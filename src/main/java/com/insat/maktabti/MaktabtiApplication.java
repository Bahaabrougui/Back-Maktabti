package com.insat.maktabti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MaktabtiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaktabtiApplication.class, args);
	}

}
