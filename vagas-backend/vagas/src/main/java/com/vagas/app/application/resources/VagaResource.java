package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.StatusVagasResponse;
import com.vagas.app.application.resources.dto.VagaRequest;
import com.vagas.app.application.services.VagaService;
import com.vagas.app.application.services.erros.VagaErrorException;
import com.vagas.app.domain.model.Vaga;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vagas")
@RequiredArgsConstructor
public class VagaResource {

    private final VagaService vagaService;

    @GetMapping(params = "id")
    public ResponseEntity<?> obter(@RequestParam("id") String id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarVaga(@RequestBody VagaRequest vagaRequest) {
        try {
            Vaga vaga = vagaService.salvar(vagaRequest);
            return ResponseEntity.ok(vaga);
        } catch (VagaErrorException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Vaga>> listar() {
        List<Vaga> vagas = vagaService.listarVagas();
        return ResponseEntity.ok(vagas);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<?> deletar(@RequestParam("id") String id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "id_analista", value = "/vagas-analista")
    public ResponseEntity<List<Vaga>> listar(@RequestParam("id_analista") String id) {
        List<Vaga> vagas = vagaService.listarVagasPorIdAnalista(id);
        return ResponseEntity.ok(vagas);
    }

    @GetMapping(value = "/vagas-dash", params = "id_analista")
    public ResponseEntity<StatusVagasResponse> listarQuantidadePorStatus(@RequestParam("id_analista") String id) {
        StatusVagasResponse statusVagasResponses = vagaService.listarQuantidadeDeVagasPorIdAnalista(id);
        return ResponseEntity.ok(statusVagasResponses);
    }
}
