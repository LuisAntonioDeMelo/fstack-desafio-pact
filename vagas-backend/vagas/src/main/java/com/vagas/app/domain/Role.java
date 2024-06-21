package com.vagas.app.domain;

public enum Role {
    ANALISTA_RH("analista"),
    CANDIDATO("candidato");

    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
