package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Requisito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    //candidados com requitos que tem na vaga

    //y vaga ten varios requisitos
    @ManyToOne
    @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    //x canditado tem varios requisitos
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private Candidato candidato;


    public Requisito(String nome, Vaga vaga) {
        this.nome = nome;
        this.vaga = vaga;
    }
}
