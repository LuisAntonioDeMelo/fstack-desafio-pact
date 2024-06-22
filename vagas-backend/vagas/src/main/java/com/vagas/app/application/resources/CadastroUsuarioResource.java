package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastro-usuarios")
@RequiredArgsConstructor
public class CadastroUsuarioResource {

    private final UsuarioService service;
    @GetMapping
    public ResponseEntity<?> criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        service.criarUsuario(criarUsuarioRequest);
        return ResponseEntity.ok().build();
    }
}
