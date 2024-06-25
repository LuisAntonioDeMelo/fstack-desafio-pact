package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RequisitoRepository extends JpaRepository<Requisito,Long> {

    void deleteByVagaId(UUID id);
}
