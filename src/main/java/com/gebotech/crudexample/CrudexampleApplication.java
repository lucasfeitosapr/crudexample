package com.gebotech.crudexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrudexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudexampleApplication.class, args);
	}

}
