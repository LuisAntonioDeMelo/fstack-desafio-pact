package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CandidatoRequest;
import com.vagas.app.application.resources.dto.CandidatoResponse;
import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.erros.UsuarioNaoEncontradoException;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.*;
import com.vagas.app.infra.repository.CandidatoRepository;
import com.vagas.app.infra.repository.UserRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidatoService implements IUsuarioService {

    private final CandidatoRepository candidatoRepository;
    private final UserRepository userRepository;
    private final NotificacaoService notificacaoService;
    private final VagaRepository vagaRepository;

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

    @Transactional
    public ResponseEntity<?> candidatarParaVaga(CandidatoRequest candidatoRequest) {
        var candidato = candidatoRepository.findById(candidatoRequest.idCandidato()).orElseThrow();
        var vaga = vagaRepository.findById(candidatoRequest.idVaga()).orElseThrow();

        if(vaga.getCandidatos().stream().anyMatch(c -> c.getId().equals(candidato.getId()))){
            return ResponseEntity.ok(new CandidatoResponse("Já candidatado para vaga."));
        }

        if(candidato.getVagas() == null) {
            candidato.setVagas(new HashSet<>());
        }

        vaga.setStatus(Status.EM_PROCESSO);
        candidato.getVagas().add(vaga);
        var candidatoSaved = candidatoRepository.save(candidato);


        notificacaoService.gerarNotificacaoCandidato(candidatoSaved, vaga.getCodigoVaga());
        notificacaoService.gerarNotificacaoAnalista(candidatoSaved, vaga);
        return ResponseEntity.ok(new CandidatoResponse("Candidato Vinculado"));
    }


}
