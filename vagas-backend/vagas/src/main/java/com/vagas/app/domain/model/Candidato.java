package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "tb_candidato")
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Candidato  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "candidato_id")
    @EqualsAndHashCode.Include
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private LocalDate dataCadastro;

    private BigDecimal pretensaoSalarial;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tb_candidato_vaga",
            joinColumns = @JoinColumn(name = "candidato_id"),
            inverseJoinColumns = @JoinColumn(name = "vaga_id")
    )
    private Set<Vaga> vagas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "candidato", fetch = FetchType.LAZY)
    private Set<Requisito> requisitos = new HashSet<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "candidato", fetch = FetchType.LAZY)
    private List<Notificacao> notificacoes;

    @PrePersist
    private void prePersist(){
        this.dataCadastro = LocalDate.now();
    }

}
