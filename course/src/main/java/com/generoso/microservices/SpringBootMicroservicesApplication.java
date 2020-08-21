package com.generoso.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Mauricio Generoso
 */
@SpringBootApplication
@EntityScan({"com.generoso.microservices.core.model"})
@EnableJpaRepositories({"com.generoso.microservices.core.repository"})
public class SpringBootMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservicesApplication.class, args);
	}
}