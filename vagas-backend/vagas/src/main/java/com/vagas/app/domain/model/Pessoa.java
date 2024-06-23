package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.AnalistaRH;
import com.vagas.app.domain.model.Candidato;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode
@Table(name = "tb_pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pessoa_id")
    private UUID id;

    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String cpf;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Candidato candidato;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private AnalistaRH analista;

}
