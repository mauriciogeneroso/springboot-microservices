package com.generoso.microservices.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.generoso.microservices.core.property.JwtConfiguration;
import com.generoso.microservices.security.filter.JwtTokenAuthorizationFilter;
import com.generoso.microservices.security.token.converter.TokenConverter;

/**
 * @author Mauricio Generoso
 */
@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
  private final TokenConverter tokenConverter;

  public SecurityCredentialsConfig(
      JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(
        new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
        UsernamePasswordAuthenticationFilter.class);
    super.configure(http);
  }

}