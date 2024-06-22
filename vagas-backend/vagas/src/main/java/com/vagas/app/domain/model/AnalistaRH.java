package com.vagas.app.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


@Entity()
@Table(name = "tb_analista_rh")
@Data
@NoArgsConstructor
public class AnalistaRH {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "analista_id")
    private UUID id;

    private String departamento;

    private LocalDate dataCadastro;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    private String cargo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "analistas", fetch = FetchType.LAZY)
    private Set<Vaga> vagas;

    @PrePersist
    private void prePersist(){
        this.dataCadastro = LocalDate.now();
    }
}
