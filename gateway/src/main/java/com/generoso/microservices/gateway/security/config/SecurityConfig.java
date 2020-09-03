package com.generoso.microservices.gateway.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.generoso.microservices.core.property.JwtConfiguration;
import com.generoso.microservices.gateway.security.filter.GatewayJwtTokenAuthorizationFilter;
import com.generoso.microservices.security.config.SecurityTokenConfig;
import com.generoso.microservices.security.token.converter.TokenConverter;

/**
 * @author Mauricio Generoso
 */
@EnableWebSecurity
public class SecurityConfig extends SecurityTokenConfig {

  private final TokenConverter tokenConverter;

  public SecurityConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(new GatewayJwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
        UsernamePasswordAuthenticationFilter.class);
    super.configure(http);
  }

}
