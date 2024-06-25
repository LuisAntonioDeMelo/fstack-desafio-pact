package com.vagas.app.vagas;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.vagas.app.application.resources.dto.CandidatosVinculadosAVagaDTO;
import com.vagas.app.application.services.CandidatoService;
import com.vagas.app.application.services.VagaService;
import com.vagas.app.domain.model.Candidato;
import com.vagas.app.domain.model.Status;
import com.vagas.app.domain.model.Vaga;
import com.vagas.app.infra.repository.CandidatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class CandidatoServiceTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @InjectMocks
    private CandidatoService vagaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarEndpointsCandidatoNotFound() {
        UUID idCandidato = UUID.randomUUID();
        when(candidatoRepository.findById(idCandidato)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            vagaService.obterVagasVinculas(idCandidato);
        });

        assertEquals(HttpStatus.NOT_FOUND.toString(), exception.getMessage());
    }

    @Test
    void deveCriarCandidatoVagaeVerificarSeDadosEstaoCorretos() {
        UUID idCandidato = UUID.randomUUID();
        Candidato candidato = new Candidato();
        candidato.setId(idCandidato);

        Vaga vaga = new Vaga();
        vaga.setId(UUID.randomUUID());
        vaga.setCodigoVaga("cod_12345");
        vaga.setStatus(Status.EM_PROCESSO); // Supondo que você tenha um enum Status
        Set<Candidato> vagaCandidatos = new HashSet<>();
        vagaCandidatos.add(candidato);
        vaga.setCandidatos(vagaCandidatos);

        candidato.setVagas(Set.of(vaga));

        when(candidatoRepository.findById(idCandidato)).thenReturn(Optional.of(candidato));
        List<CandidatosVinculadosAVagaDTO> result = vagaService.obterVagasVinculas(idCandidato);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(idCandidato, result.get(0).getIdCandidato());
        assertEquals(vaga.getId(), result.get(0).getIdVaga());
        assertEquals("cod_12345", result.get(0).getCodigoVaga());
        assertEquals(Status.EM_PROCESSO.toString(), result.get(0).getStatusVaga());
    }

}