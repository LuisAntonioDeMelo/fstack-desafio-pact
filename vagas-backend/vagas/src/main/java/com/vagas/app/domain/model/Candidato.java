package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.Role;
import com.vagas.app.domain.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity()
@Table(name = "TB_CANDIDATO")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Candidato extends Pessoa {

    @Column(name = "codigo_cadidato")
    private UUID codigo;

    private LocalDate dataCadastro;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "vagas", fetch = FetchType.LAZY)
    private Set<Vaga> vagas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY)
    private Set<Requisito> requisitos = new HashSet<>();

    @Override
    public void criarPessoaRole(Role role) {
        super.criarPessoaRole(Role.CANDIDATO);
    }
}
