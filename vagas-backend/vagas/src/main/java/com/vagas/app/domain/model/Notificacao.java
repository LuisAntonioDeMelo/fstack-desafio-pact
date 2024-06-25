package com.vagas.app.domain.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_notificacao")
@Data
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoNotificacao;
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "analista_id")
    private AnalistaRH analista;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;

}
