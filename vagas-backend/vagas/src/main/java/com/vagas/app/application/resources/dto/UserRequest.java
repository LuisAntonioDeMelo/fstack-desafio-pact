package com.vagas.app.application.resources.dto;

import com.vagas.app.domain.Role;

public record UserRequest(String login, Role role) {
}
