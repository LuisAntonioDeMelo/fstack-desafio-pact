package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {

    @Query("select v from Vaga v join v.analistas an left join v.candidatos can where an.id =:id")
    Optional<List<Vaga>> obterVagasPorIdAnalista(@Param("id") String id);
}
