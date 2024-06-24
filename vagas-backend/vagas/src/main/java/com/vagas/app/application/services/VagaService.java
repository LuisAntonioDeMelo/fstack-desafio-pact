package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.domain.model.AnalistaRH;
import com.vagas.app.domain.model.Requisito;
import com.vagas.app.domain.model.Vaga;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import com.vagas.app.infra.repository.RequisitoRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VagaService {

    private final VagaRepository vagaRepository;
    private final AnalistaRHRepository analistaRHRepository;
    private final RequisitoRepository requisitoRepository;


    public List<Vaga> listarVagasPorIdAnalista(String id) {
        Optional<List<Vaga>> vagas = vagaRepository.obterVagasPorIdAnalista(id);
        return vagas.orElseGet(Collections::emptyList);
    }

    public List<Vaga> listarVagas() {
        Optional<List<Vaga>> vagas = Optional.of(vagaRepository.findAll());
        return vagas.orElseGet(Collections::emptyList);
    }

    @Transactional
    public Vaga salvar(VagaRequest vagaRequest) {
        Vaga vaga = vagaRequest.toModel();
        vaga.setCodigoVaga(UUID.randomUUID().toString());

        UUID uuid = UUID.fromString(vagaRequest.getIdAnalistaResp());
        var analistaRH = analistaRHRepository.findById(uuid);

        analistaRH.ifPresent(rh -> vaga.setAnalistas(new HashSet<>(Set.of(rh))));
        var vagaSaved = vagaRepository.save(vaga);
        salvarRequisitosDaVaga(vagaRequest, vagaSaved);

        return vagaSaved;
    }

    private void salvarRequisitosDaVaga(VagaRequest vagaRequest, Vaga vagaSaved) {
        var requisitos = vagaRequest.getRequisitos().stream().map(r -> {
            return new Requisito(r.getNome(), vagaSaved);
        }).toList();
        requisitoRepository.saveAll(requisitos);
    }
}
