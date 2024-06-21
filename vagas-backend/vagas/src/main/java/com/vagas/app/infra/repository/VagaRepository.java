package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {
}
