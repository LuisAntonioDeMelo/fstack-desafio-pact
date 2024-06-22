package com.vagas.app.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoNotificacao;
    private String mensagem;
    //usuario x canditatou a vaga y
    //vaga foi fechada
    //vaga foi descontinuada
    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;
}

//essa vaga tem essas notificações
//o analista ve as notificações de todas as vagas relacionadas a ele
