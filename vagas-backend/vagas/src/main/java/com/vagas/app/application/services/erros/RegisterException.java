package com.vagas.app.application.services.erros;

public class RegisterException extends RuntimeException {

    public RegisterException(String message) {
        super(message);
    }
}
