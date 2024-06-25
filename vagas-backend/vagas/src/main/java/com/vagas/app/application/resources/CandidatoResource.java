package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CandidatoRequest;
import com.vagas.app.application.resources.dto.CandidatoResponse;
import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.application.services.CandidatoService;
import com.vagas.app.application.services.erros.VagaErrorException;
import com.vagas.app.domain.model.Vaga;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    @PreAuthorize("hasAuthority('ROLE_CANDIDATO')")
    @GetMapping(value = "/exibir-candidatos", params = "id")
    public ResponseEntity<List<CandidatosVinculadosAVagaDTO>> candidatosInteressadosVaga(@RequestParam("id") UUID id) {
        var vinculadosAVagasCriada =  candidatoService.obterVagasVinculas(id);
        return ResponseEntity.ok(vinculadosAVagasCriada);
    }


}
