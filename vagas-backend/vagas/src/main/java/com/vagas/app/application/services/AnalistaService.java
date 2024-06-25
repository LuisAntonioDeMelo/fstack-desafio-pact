package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.erros.UsuarioNaoEncontradoException;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.domain.User;
import com.vagas.app.domain.model.*;
import com.vagas.app.infra.repository.AnalistaRHRepository;
import com.vagas.app.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<CandidatosVinculadosAVagaDTO> obterCandidatosVinculadosAVagasCriada(UUID idAnalista) {
        var analista = analistaRHRepository.findById(idAnalista);
        if (analista.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return getCandidatosVinculadosAVagaDTOS(analista);
    }

    private static List<CandidatosVinculadosAVagaDTO> getCandidatosVinculadosAVagaDTOS(Optional<AnalistaRH> analista) {
        var vagas = analista.get().getVagas();
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
