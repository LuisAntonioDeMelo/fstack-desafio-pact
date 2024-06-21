package com.vagas.app.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_NOTIFICACAO")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoNotificacao;
    private String mensagem;
    //usuario x canditatou a vaga y
    //vaga foi fechada
    //vaga foi descontinuada
    @OneToOne(mappedBy = "vaga", cascade = CascadeType.ALL)
    private Vaga vaga;
}

//essa vaga tem essas notificações
//o analista ve as notificações de todas as vagas relacionadas a ele
