package com.vagas.app.application.service;

import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.model.Candidato;
import com.vagas.app.infra.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CanditadoService {

    private final CandidatoRepository candidatoRepository;
    @Transactional
    public Candidato salvarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }
}
