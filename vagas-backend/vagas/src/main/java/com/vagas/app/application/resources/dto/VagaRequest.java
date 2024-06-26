package com.vagas.app.application.resources.dto;

import com.vagas.app.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VagaRequest {
    private String id;
    private String idAnalistaResp;
    private String codigoVaga;
    private LocalDate dataCriacao;
    private LocalDate dataVencimento;
    private String descricao;
    private String localizacao;
    private String prioridade;
    private List<Requisito> requisitos;
    private BigDecimal salario;
    private String status;
    private String tipoVaga;
    private String titulo;

    public Vaga toModel() {
        return Vaga.builder()
                .id(ObjectUtils.isEmpty(id) ? null :UUID.fromString(id))
                .titulo(titulo)
                .codigoVaga(codigoVaga)
                .dataCriacao(dataCriacao)
                .dataVencimento(dataVencimento)
                .status(Status.valueOf(status))
                .prioridade(Prioridade.valueOf(prioridade))
                .localizacao(localizacao)
                .descricao(descricao)
                .salario(salario)
                .tipoVaga(TipoVaga.valueOf(tipoVaga))
                .build();
    }
    public Vaga toData(Vaga vagaDb) {
        return Vaga.builder()
                .id(ObjectUtils.isEmpty(id) ? null :UUID.fromString(id))
                .titulo(titulo)
                .codigoVaga(codigoVaga)
                .dataCriacao(dataCriacao)
                .dataVencimento(dataVencimento)
                .status(Status.valueOf(status))
                .prioridade(Prioridade.valueOf(prioridade))
                .localizacao(localizacao)
                .descricao(descricao)
                .salario(salario)
                .analistas(vagaDb.getAnalistas())
                .tipoVaga(TipoVaga.valueOf(tipoVaga))
                .build();
    }
}
