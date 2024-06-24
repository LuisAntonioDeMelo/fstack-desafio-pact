package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.resources.dto.UserRequest;
import com.vagas.app.application.services.UserService;
import com.vagas.app.application.services.UsuarioService;
import com.vagas.app.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> obterDadosUsuario(@RequestParam("id") UserRequest userRequest) {
        var user = userService.obterDadosUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("listAll")
    public ResponseEntity<?> obterTodos() {
        var user = userService.obterTodos();
        return ResponseEntity.ok(user);
    }

}
