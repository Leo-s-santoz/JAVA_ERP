package com.leonardo.controle_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleEstoqueApplication {

	public static void main(String[] args) {

		EnvConfig.load();

		SpringApplication.run(ControleEstoqueApplication.class, args);
	}

}
