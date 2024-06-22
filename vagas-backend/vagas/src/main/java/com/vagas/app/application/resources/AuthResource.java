package com.vagas.app.application.resources;

import com.vagas.app.application.resources.dto.AuthenticationRequest;
import com.vagas.app.application.resources.dto.LoginResponse;
import com.vagas.app.application.resources.dto.Register;
import com.vagas.app.application.services.UserService;
import com.vagas.app.application.services.erros.RegisterException;
import com.vagas.app.config.Token;
import com.vagas.app.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthResource {

    private final UserService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest data) {
        try {
            Token token = service.obterToken(data);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        try {
            User user = service.criarUser(register);
            URI headerLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                    .query("user={user_id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            return ResponseEntity.created(headerLocation).build();
        } catch (RegisterException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
