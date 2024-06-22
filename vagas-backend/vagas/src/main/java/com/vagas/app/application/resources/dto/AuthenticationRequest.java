package com.vagas.app.application.resources.dto;

public record AuthenticationRequest(String username, String password) {
    public AuthenticationRequest withUsername(String username) {
        return new AuthenticationRequest(username, this.password);
    }
    public AuthenticationRequest withPassword(String password) {
        return new AuthenticationRequest(this.username, password);
    }

}
