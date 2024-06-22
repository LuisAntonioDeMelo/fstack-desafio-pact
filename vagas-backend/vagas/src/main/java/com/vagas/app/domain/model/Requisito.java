package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Prioridade prioridade;


    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;
}
