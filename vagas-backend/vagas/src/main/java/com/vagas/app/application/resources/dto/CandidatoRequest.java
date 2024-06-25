package com.vagas.app.application.resources.dto;

import java.util.UUID;

public record CandidatoRequest(
        UUID idVaga,
        UUID idCandidato,
        String codVaga
) {
}
