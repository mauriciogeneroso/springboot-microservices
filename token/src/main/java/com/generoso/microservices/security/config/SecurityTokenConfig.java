package com.generoso.microservices.security.config;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import com.generoso.microservices.core.property.JwtConfiguration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityTokenConfig extends WebSecurityConfigurerAdapter {

  protected final JwtConfiguration jwtConfiguration;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling().authenticationEntryPoint((req, res, ex) ->
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers(jwtConfiguration.getUrlLogin()).permitAll()
        .antMatchers("/courses/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated();
  }
}
