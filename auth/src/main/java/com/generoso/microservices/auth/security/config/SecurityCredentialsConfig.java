package com.generoso.microservices.auth.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.generoso.microservices.auth.security.filter.JwtUsernameAndPasswordAuthenticationFilter;
import com.generoso.microservices.core.property.JwtConfiguration;
import com.generoso.microservices.security.config.SecurityTokenConfig;
import com.generoso.microservices.security.token.converter.TokenConverter;
import com.generoso.microservices.security.token.creator.TokenCreator;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {

  private final UserDetailsService userDetailsService;
  private final TokenCreator tokenCreator;
  private final TokenConverter tokenConverter;

  public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration,
      UserDetailsService userDetailsService,
      TokenCreator tokenCreator, TokenConverter tokenConverter) {
    super(jwtConfiguration);
    this.userDetailsService = userDetailsService;
    this.tokenCreator = tokenCreator;
    this.tokenConverter = tokenConverter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .addFilter(
            new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),
                jwtConfiguration, tokenCreator));
    super.configure(http);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
