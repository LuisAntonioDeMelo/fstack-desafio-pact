package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Requisito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisitoRepository extends JpaRepository<Requisito,Long> {
}
