package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Entity()
@Table(name = "tb_analista_rh")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AnalistaRH {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "analista_id")
    private UUID id;

    private String departamento;

    private LocalDate dataCadastro;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private String empresa;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "analistas", fetch = FetchType.LAZY)
    private Set<Vaga> vagas;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "analista", fetch = FetchType.LAZY)
    private List<Notificacao> notificacoes;

    @PrePersist
    private void prePersist(){
        this.dataCadastro = LocalDate.now();
    }
}
