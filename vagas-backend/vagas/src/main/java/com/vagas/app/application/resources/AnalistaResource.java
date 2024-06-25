package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.services.AnalistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("analistas")
@RequiredArgsConstructor
public class AnalistaResource {

    private final AnalistaService analistaService;
    @PreAuthorize("hasAuthority('ROLE_ANALISTA')")
    @GetMapping(value = "/exibir-candidatos", params = "id")
    public ResponseEntity<List<CandidatosVinculadosAVagaDTO>> candidatosInteressadosVaga(@RequestParam("id") UUID idAnalista) {
        var vinculadosAVagasCriada =  analistaService.obterCandidatosVinculadosAVagasCriada(idAnalista);
        return ResponseEntity.ok(vinculadosAVagasCriada);
    }
}
