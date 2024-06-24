package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.erros.UsuarioNaoEncontradoException;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.Candidato;
import com.vagas.app.domain.model.Pessoa;
import com.vagas.app.infra.repository.CandidatoRepository;
import com.vagas.app.infra.repository.PessoaRepository;
import com.vagas.app.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoService implements IUsuarioService {

    private final CandidatoRepository candidatoRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void criarUsuario(CriarUsuarioRequest req, Optional<User> user) {
        if (user.isEmpty()) {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
        User userDb = user.get();
        var candidato = criarCandidato(req, userDb);
        candidatoRepository.save(candidato);
    }

    private Candidato criarCandidato(CriarUsuarioRequest req, User user) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(req.nome());
        pessoa.setEmail(req.email());
        pessoa.setTelefone(req.telefone());
        pessoa.setCpf(req.cpf());
        pessoa.setEndereco(req.endereco());
        pessoa.setUser(user);

        user.setPessoa(pessoa);
        var userUpdate = userRepository.save(user);

        Candidato candidato = new Candidato();
        candidato.setPretensaoSalarial(BigDecimal.ZERO);
        candidato.setDataCadastro(LocalDate.now());
        candidato.setPessoa(userUpdate.getPessoa());

        return candidato;
    }

    @Override
    public void deletarUsuario(String userId) {

    }
}
