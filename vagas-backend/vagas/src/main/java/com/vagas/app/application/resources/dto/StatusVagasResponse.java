package com.vagas.app.application.resources.dto;

public record StatusVagasResponse(
        int qtdCriada,
        int qtdAberta,
        int qtdEmProcesso,
        int qtdFechada,
        int qtdCancelada
) {
}
