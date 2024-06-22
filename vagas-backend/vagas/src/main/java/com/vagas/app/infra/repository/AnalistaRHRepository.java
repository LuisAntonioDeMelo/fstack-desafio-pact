package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.AnalistaRH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalistaRHRepository extends JpaRepository<AnalistaRH, UUID> {
}
