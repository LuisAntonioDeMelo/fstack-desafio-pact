package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.application.services.patterns.UsuarioComponent;
import com.vagas.app.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioComponent usuarioComponent;
    private final UserRepository userRepository;

    public void criarUsuario(CriarUsuarioRequest criarUsuarioRequest) {
        String role = criarUsuarioRequest.role().toLowerCase();
        IUsuarioService service = usuarioComponent.usuarioServices().get(role);
        if (service != null) {
            var user = userRepository.findById(criarUsuarioRequest.usuarioId());
            service.criarUsuario(criarUsuarioRequest, user);
        } else {
            throw new IllegalArgumentException("Serviço não encontrado para o papel: " + role);
        }
    }

}