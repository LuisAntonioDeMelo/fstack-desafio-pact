package com.vagas.app.application.resources;

import com.vagas.app.application.services.NotificacaoResponse;
import com.vagas.app.application.services.NotificacaoService;
import com.vagas.app.domain.model.Notificacao;
import com.vagas.app.domain.model.Vaga;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("notificacoes")
@RequiredArgsConstructor
public class NoficacaoResource {

    private final NotificacaoService notificacaoService;

    @GetMapping(params = "id", value = "/candidato")
    public ResponseEntity<List<NotificacaoResponse>> listarNotificacoesCandidadto(@RequestParam("id") UUID id) {
        List<NotificacaoResponse> responses =  notificacaoService.obterNotificacaoPorCandidato(id);
        return ResponseEntity.ok(responses);
    }

    @GetMapping(params = "id", value = "/analista")
    public ResponseEntity<List<NotificacaoResponse>> listarNotificacoesAnalista(@RequestParam("id") UUID id) {
        List<NotificacaoResponse> responses =  notificacaoService.obterNotificacaoPorAnalista(id);
        return ResponseEntity.ok(responses);
    }

}
