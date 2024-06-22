package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.AnalistaRH;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalistaService implements IUsuarioService {

    private final AnalistaRHRepository analistaRHRepository;

    @Override
    @Transactional
    public User criarUsuario(CriarUsuarioRequest criarUsuarioRequest, Optional<User> user) {
        Pessoa pessoa = new Pessoa();
        var an = new AnalistaRH();
        an.setPessoa(pessoa);
        analistaRHRepository.save(an);
        return user.get();
    }

    @Override
    public void deletarUsuario(CriarUsuarioRequest criarUsuarioRequest) {

    }
}
