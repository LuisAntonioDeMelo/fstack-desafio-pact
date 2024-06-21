package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;


@Entity()
@Table(name = "TB_ANALISTA_RH")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnalistaRH extends Pessoa {

    private String cargo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "vagas", fetch = FetchType.LAZY)
    private Set<Vaga> vagas;

    @Override
    public void criarPessoaRole(Role role) {
        super.criarPessoaRole(Role.ANALISTA_RH);
    }

}
