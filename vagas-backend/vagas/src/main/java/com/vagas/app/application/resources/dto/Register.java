package com.vagas.app.application.resources.dto;

import com.vagas.app.domain.Role;

public record Register(
        String username,
        String password,
        Role role
) {
}
