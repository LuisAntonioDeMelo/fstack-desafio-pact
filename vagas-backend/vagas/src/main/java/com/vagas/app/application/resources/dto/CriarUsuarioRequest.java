package com.vagas.app.application.resources.dto;

public record CriarUsuarioRequest(
         String id,
         String nome,
         String email,
         String endereco,
         String telefone,
         String cpf,
         String role
) {}
