package com.vagas.app.application.services.erros;

public class VagaErrorException extends RuntimeException {
    public VagaErrorException(String message) {
        super(message);
    }
}
