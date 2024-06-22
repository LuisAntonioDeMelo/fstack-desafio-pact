package com.vagas.app.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
    private String token;
    private int expiration;
    private String hash;
}
