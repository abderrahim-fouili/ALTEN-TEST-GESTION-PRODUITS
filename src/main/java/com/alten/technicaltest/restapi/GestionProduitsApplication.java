package com.alten.technicaltest.restapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "ALTEN TECHNICAL TEST : DEFAULT SERVER URL")})
@SpringBootApplication
public class GestionProduitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionProduitsApplication.class, args);
	}

}
