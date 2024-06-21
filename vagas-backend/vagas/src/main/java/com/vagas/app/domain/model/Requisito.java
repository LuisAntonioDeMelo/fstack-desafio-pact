package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Requisito {

    private String nome;
    private Prioridade prioridade;

    @OneToOne(mappedBy = "vaga", cascade = CascadeType.ALL)
    private Vaga vaga;
}
