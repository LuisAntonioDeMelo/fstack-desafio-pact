package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidatoRepository extends JpaRepository<Candidato, UUID> {
}
