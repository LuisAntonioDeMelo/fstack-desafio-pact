package com.vagas.app.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Vagas - Projeto Pacto")
                        .description("Disponibilizando Recursos das Apis Sistema de vagas")
                        .version("v1")
                        .contact(new Contact()
                                .name("Luis Antônio de Melo Gomes")
                                .url("https://github.com/")
                                .email("tyluis20@gmail.com"))
                        .license(new License()
                                .name("API License")
                                .url("https://github.com/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Full API Documentation")
                        .url("https://github.com/"));
    }
}