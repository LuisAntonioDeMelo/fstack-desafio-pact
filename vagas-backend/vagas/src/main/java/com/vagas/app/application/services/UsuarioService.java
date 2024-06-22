package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.CriarUsuarioRequest;
import com.vagas.app.application.services.patterns.IUsuarioService;
import com.vagas.app.application.services.patterns.ServiceSelector;
import com.vagas.app.domain.User;
import com.vagas.app.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final ServiceSelector serviceSelector;
    private final UserRepository userRepository;

    public User criarUsuario(CriarUsuarioRequest criarUsuarioRequest) {
        String role = criarUsuarioRequest.role().toLowerCase();
        IUsuarioService service = serviceSelector.usuarioServices().get(role);
        if (service != null) {
            var user = userRepository.findByLogin(criarUsuarioRequest.email());
             return service.criarUsuario(criarUsuarioRequest, user);
        } else {
            throw new IllegalArgumentException("Serviço não encontrado para o papel: " + role);
        }
    }

}