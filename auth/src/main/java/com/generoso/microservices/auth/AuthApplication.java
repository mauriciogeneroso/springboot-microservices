package com.generoso.microservices.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.generoso.microservices.core.property.JwtConfiguration;

/**
 * @author Mauricio Generoso
 */
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.generoso.microservices.core.model"})
@ComponentScan("com.generoso.microservices")
@EnableJpaRepositories({"com.generoso.microservices.core.repository"})
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
