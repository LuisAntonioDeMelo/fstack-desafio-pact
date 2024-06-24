package com.vagas.app.domain.model;

public enum TipoVaga {
    REMOTO("remoto"), HIBRIDO("hibrido"), PRESENCIAL("presencial");

    private final String tipo;

    TipoVaga(String tipo){
        this.tipo = tipo;
    }
    public String getTipo(){
        return tipo;
    }
}
