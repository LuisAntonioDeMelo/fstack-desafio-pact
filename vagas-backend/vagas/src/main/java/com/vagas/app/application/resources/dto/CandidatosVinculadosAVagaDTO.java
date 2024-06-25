package com.vagas.app.application.resources.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CandidatosVinculadosAVagaDTO {
    private String nome;
    private UUID idCandidato;
    private UUID idVaga;
    private List<String> habilidades;
    private String codigoVaga;
    private String statusVaga;
}
