package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_vaga")
@NoArgsConstructor
@Data
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vaga_id")
    private UUID id;

    private String codigoVaga;

    private String titulo;

    private String descricao;

    private LocalDate dataCriacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private LocalDate dataVencimento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "vagas", fetch = FetchType.LAZY)
    private Set<Candidato> cadidatos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_vaga_analista_rh",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "analista_id")
    )
    private Set<AnalistaRH> analistas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY)
    private List<Requisito> requisitos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY)
    private List<Notificacao> notificacoes;

}
