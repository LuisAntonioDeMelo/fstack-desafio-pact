package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.erros.UsuarioNaoEncontradoException;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.AnalistaRH;
import com.vagas.app.domain.model.Pessoa;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import com.vagas.app.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalistaService implements IUsuarioService {

    private final AnalistaRHRepository analistaRHRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void criarUsuario(CriarUsuarioRequest request, Optional<User> user) {
        if (user.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }

        var candidato = criarAnalista(request, user.get());
        analistaRHRepository.save(candidato);
    }

    private AnalistaRH criarAnalista(CriarUsuarioRequest req, User user) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(req.nome());
        pessoa.setEmail(req.email());
        pessoa.setTelefone(req.telefone());
        pessoa.setCpf(req.cpf());
        pessoa.setEndereco(req.endereco());
        pessoa.setUser(user);

        user.setPessoa(pessoa);
        var userUpdate = userRepository.save(user);

        AnalistaRH analistaRH = new AnalistaRH();
        analistaRH.setDataCadastro(LocalDate.now());
        analistaRH.setDepartamento("RH - TI");
        analistaRH.setEmpresa("XXX Company");
        analistaRH.setPessoa(userUpdate.getPessoa());

        return analistaRH;
    }


}
