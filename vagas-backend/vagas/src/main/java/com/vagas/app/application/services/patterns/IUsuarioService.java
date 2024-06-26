package com.vagas.app.application.services.patterns;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.domain.User;

import java.util.Optional;

public interface IUsuarioService {
    void criarUsuario(CriarUsuarioRequest criarUsuarioRequest, Optional<User> user);

}
