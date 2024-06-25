package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.StatusVagasResponse;
import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.domain.model.Requisito;
import com.vagas.app.domain.model.Status;
import com.vagas.app.domain.model.Vaga;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import com.vagas.app.infra.repository.RequisitoRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final AnalistaRHRepository analistaRHRepository;
    private final RequisitoRepository requisitoRepository;


    public List<Vaga> listarVagasPorIdAnalista(String id) {
        Optional<List<Vaga>> vagas = vagaRepository.obterVagasPorIdAnalista(UUID.fromString(id));
        return vagas.orElseGet(Collections::emptyList);
    }

    public List<Vaga> listarVagas() {
        Optional<List<Vaga>> vagas = Optional.of(vagaRepository.findAll());
        return vagas.orElseGet(Collections::emptyList);
    }

    @Transactional
    public Vaga salvar(VagaRequest vagaRequest) {
        Vaga vaga = vagaRequest.toModel();
        vaga.setCodigoVaga("cod_" + UUID.randomUUID().toString().substring(1, 7));

        UUID uuid = UUID.fromString(vagaRequest.getIdAnalistaResp());
        var analistaRH = analistaRHRepository.findById(uuid);

        analistaRH.ifPresent(rh -> vaga.setAnalistas(new HashSet<>(Set.of(rh))));
        var vagaSaved = vagaRepository.save(vaga);
        salvarRequisitosDaVaga(vagaRequest, vagaSaved);

        return vagaSaved;
    }

    private void salvarRequisitosDaVaga(VagaRequest vagaRequest, Vaga vagaSaved) {
        requisitoRepository.deleteByVagaId(vagaSaved.getId());
        var requisitos = vagaRequest.getRequisitos().stream().map(r -> {
            return new Requisito(r.getNome(), vagaSaved);
        }).toList();

        requisitoRepository.saveAll(requisitos);
    }

    @Transactional
    public void deletarVaga(String id) {
        var vaga = vagaRepository.findById(UUID.fromString(id));
        if (vaga.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
        }
        vagaRepository.delete(vaga.get());
    }

    public StatusVagasResponse listarQuantidadeDeVagasPorIdAnalista(String id) {
        List<Vaga> vagas = vagaRepository.obterVagasPorIdAnalista(UUID.fromString(id)).orElseThrow();

        Map<Status, Long> contagemPorStatus = vagas.stream()
                .collect(Collectors.groupingBy(Vaga::getStatus, Collectors.counting()));

        int qtdVagasCriadas = vagas.size();
        int qtdVagasAbertas = contagemPorStatus.getOrDefault(Status.ABERTA, 0L).intValue();
        int qtdVagasFechadas = contagemPorStatus.getOrDefault(Status.FECHADA, 0L).intValue();
        int qtdVagasCanceladas = contagemPorStatus.getOrDefault(Status.CANCELADA, 0L).intValue();
        int qtdVagasEmProcesso = contagemPorStatus.getOrDefault(Status.EM_PROCESSO, 0L).intValue();

        return new StatusVagasResponse(qtdVagasCriadas, qtdVagasAbertas, qtdVagasFechadas, qtdVagasCanceladas, qtdVagasEmProcesso);
    }
}
