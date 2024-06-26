package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.resources.dto.StatusVagasResponse;
import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.domain.model.Requisito;
import com.vagas.app.domain.model.Status;
import com.vagas.app.domain.model.Vaga;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import com.vagas.app.infra.repository.RequisitoRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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

        adicionarAnalistaResposavelVaga(vagaRequest, vaga);
        var vagaSaved = vagaRepository.save(vaga);

        salvarRequisitosDaVaga(vagaRequest, vagaSaved);
        return vagaSaved;
    }

    private void salvarRequisitosDaVaga(VagaRequest vagaRequest, Vaga vaga) {
        List<Requisito> requisitos = vagaRequest.getRequisitos().stream()
                .map(r -> new Requisito(r.getNome(), vaga))
                .collect(Collectors.toList());
        requisitoRepository.saveAll(requisitos);
    }

    @Transactional
    public Vaga editar(VagaRequest vagaRequest) {
        Vaga vagaDb = vagaRepository.obterVagaCompleta(UUID.fromString(vagaRequest.getId()));
        requisitoRepository.deleteAll(vagaDb.getRequisitos());

        BeanUtils.copyProperties(vagaRequest.toData(vagaDb), vagaDb);

        List<Requisito> requisitosRequest = vagaRequest.getRequisitos();
        requisitosRequest.forEach(r -> r.setVaga(vagaDb));
        vagaDb.setRequisitos(requisitosRequest);

        return vagaRepository.save(vagaDb);
    }

    private void adicionarAnalistaResposavelVaga(VagaRequest vagaRequest, Vaga vaga) {
        UUID uuid = UUID.fromString(vagaRequest.getIdAnalistaResp());
        var analistaRH = analistaRHRepository.findById(uuid);
        analistaRH.ifPresent(rh -> vaga.setAnalistas(new HashSet<>(Set.of(rh))));
    }

    @Transactional
    public void deletarVaga(String id) {
        var vaga = vagaRepository.findById(UUID.fromString(id));
        if (vaga.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
        }
        if (vagaTemCandidato(vaga.get())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vaga contém candidatos em processo!");
        }
        vagaRepository.delete(vaga.get());
    }

    private boolean vagaTemCandidato(Vaga vaga) {
        return !vaga.getCandidatos().isEmpty();
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
