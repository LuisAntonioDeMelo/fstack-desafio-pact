package com.vagas.app.infra.repository;

import com.vagas.app.domain.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {
    List<Notificacao> findByCandidatoId(UUID id);

    List<Notificacao> findByAnalistaId (UUID id);
}
