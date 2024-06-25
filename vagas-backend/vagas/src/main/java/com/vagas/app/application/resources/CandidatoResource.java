package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CandidatoRequest;
import com.vagas.app.application.resources.dto.CandidatoResponse;
import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.application.services.CandidatoService;
import com.vagas.app.application.services.erros.VagaErrorException;
import com.vagas.app.domain.model.Vaga;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("candidatos")
@RequiredArgsConstructor
public class CandidatoResource {

    private final CandidatoService candidatoService;
    @PostMapping("/cadastrar-candidatura")
    public ResponseEntity<?> candidatarParaVaga(@RequestBody CandidatoRequest candidatoRequest) {
        try {
            return candidatoService.candidatarParaVaga(candidatoRequest);
        } catch (VagaErrorException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
