package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.Pessoa;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.Candidato;
import com.vagas.app.infra.repository.CandidatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoService implements IUsuarioService {

    private final CandidatoRepository candidatoRepository;

    @Override
    @Transactional
    public User criarUsuario(CriarUsuarioRequest criarUsuarioRequest, Optional<User> user) {
        if(user.isPresent()) {
            Pessoa pessoa =  user.get().getPessoa();
            Candidato candidato = new Candidato();
            pessoa.setEmail(criarUsuarioRequest.email());
            candidato.setPessoa(pessoa);
            candidatoRepository.save(candidato);
        }
        return user.get();
    }

    @Override
    public void deletarUsuario(CriarUsuarioRequest criarUsuarioRequest) {

    }
}
