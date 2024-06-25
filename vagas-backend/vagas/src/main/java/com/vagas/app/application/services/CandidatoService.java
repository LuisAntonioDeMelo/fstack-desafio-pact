package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CandidatoRequest;
import com.vagas.app.application.resources.dto.CandidatoResponse;
import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.erros.UsuarioNaoEncontradoException;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.*;
import com.vagas.app.infra.repository.CandidatoRepository;
import com.vagas.app.infra.repository.UserRepository;
import com.vagas.app.infra.repository.VagaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

    public List<CandidatosVinculadosAVagaDTO> obterVagasVinculas(UUID idCandidato) {
        var candidato = candidatoRepository.findById(idCandidato);
        if (candidato.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return getCandidatosVinculadosAVagaDTOS(candidato);
    }

    private static List<CandidatosVinculadosAVagaDTO> getCandidatosVinculadosAVagaDTOS(Optional<Candidato> candidato) {
        var vagas = candidato.get().getVagas();
        List<CandidatosVinculadosAVagaDTO> vinculadosAVagaList = new ArrayList<>();
        if (!vagas.isEmpty()) {
            for (Vaga vaga : vagas) {
                Set<Candidato> vagaCandidatos = vaga.getCandidatos();

                if (!vagaCandidatos.isEmpty())
                    vagaCandidatos.forEach(
                            ca -> {
                                var dtoVinculadosAVaga = CandidatosVinculadosAVagaDTO.builder()
                                        .idCandidato(ca.getId())
                                        .habilidades(ca.getRequisitos().stream().map(Requisito::getNome).toList())
                                        .idVaga(vaga.getId())
                                        .nome(ca.getPessoa().getNome())
                                        .codigoVaga(vaga.getCodigoVaga())
                                        .statusVaga(vaga.getStatus().toString())
                                        .build();
                                vinculadosAVagaList.add(dtoVinculadosAVaga);
                            }
                    );
            }
        }
        return vinculadosAVagaList;
    }



}
