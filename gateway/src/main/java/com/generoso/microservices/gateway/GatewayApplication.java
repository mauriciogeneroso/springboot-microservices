package com.generoso.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.generoso.microservices.core.property.JwtConfiguration;
import com.generoso.microservices.gateway.filter.SimpleFilter;

/**
 * @author Mauricio Generoso
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@ComponentScan("com.generoso.microservices")
@EnableConfigurationProperties(value = JwtConfiguration.class)
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
