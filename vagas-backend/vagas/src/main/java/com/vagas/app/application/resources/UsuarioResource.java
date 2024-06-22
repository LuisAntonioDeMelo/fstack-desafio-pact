package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.resources.dto.UserRequest;
import com.vagas.app.application.services.UserService;
import com.vagas.app.application.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;
    private final UserService userService;
    @PostMapping("criar")
    public ResponseEntity<?> criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        usuarioService.criarUsuario(criarUsuarioRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> obterDadosUsuario(@RequestBody UserRequest userRequest) {
        var user = userService.obterDadosUser(userRequest);
        return ResponseEntity.ok(user);
    }
}
