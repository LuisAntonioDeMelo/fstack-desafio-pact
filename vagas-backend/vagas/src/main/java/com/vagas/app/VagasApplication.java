package com.vagas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class VagasApplication {

	public static void main(String[] args) {
		SpringApplication.run(VagasApplication.class, args);
	}

	@GetMapping("/")
	public String index(final Model model) {
		model.addAttribute("title", "Api Vagas - Spring Boot");
		model.addAttribute("msg", "docker container :: start !");
		return "index";
	}

}
