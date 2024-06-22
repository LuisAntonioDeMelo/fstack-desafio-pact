package com.vagas.app.application.services.patterns;

import com.vagas.app.application.services.AnalistaService;
import com.vagas.app.application.services.CandidatoService;
import com.vagas.app.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
@RequiredArgsConstructor
public class UsuarioComponent {

    private final CandidatoService candidatoService;
    private final AnalistaService analistaService;

    public Map<String, IUsuarioService> usuarioServices() {
        Map<String, IUsuarioService> services = new HashMap<>();
        services.put(Role.ANALISTA_RH.getRole(), analistaService);
        services.put(Role.CANDIDATO.getRole(), candidatoService);
        return services;
    }
}
