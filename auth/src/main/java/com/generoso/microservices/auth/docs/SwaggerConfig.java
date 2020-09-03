package com.generoso.microservices.auth.docs;

import org.springframework.context.annotation.Configuration;
import com.generoso.microservices.core.docs.BaseSwaggerConfig;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Mauricio Generoso
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

  public SwaggerConfig() {
    super("com.generoso.microservices.auth.endpoint.controller");
  }

}
