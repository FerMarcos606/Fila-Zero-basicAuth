package com.filazero.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FilaZeroBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilaZeroBackendApplication.class, args);
	}


}
