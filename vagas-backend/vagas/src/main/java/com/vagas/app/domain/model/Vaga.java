package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_vaga")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vaga_id")
    private UUID id;

    private String codigoVaga;

    private String titulo;

    private String descricao;

    private LocalDate dataCriacao;

    private String localizacao;

    private BigDecimal salario;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Enumerated(EnumType.STRING)
    private TipoVaga tipoVaga;

    private LocalDate dataVencimento;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "vagas", fetch = FetchType.LAZY)
    private Set<Candidato> candidatos;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_vaga_analista_rh",
            joinColumns = @JoinColumn(name = "vaga_id"),
            inverseJoinColumns = @JoinColumn(name = "analista_id")
    )
    private Set<AnalistaRH> analistas;


    @OneToMany(mappedBy = "vaga", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Requisito> requisitos;

}
