package com.generoso.microservices.security.filter;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import com.generoso.microservices.core.property.JwtConfiguration;
import com.generoso.microservices.security.token.converter.TokenConverter;
import com.generoso.microservices.security.util.SecurityContextUtil;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mauricio Generoso
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtTokenAuthorizationFilter extends OncePerRequestFilter {
  protected final JwtConfiguration jwtConfiguration;
  protected final TokenConverter tokenConverter;

  @Override
  @SuppressWarnings("Duplicates")
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    String header = request.getHeader(jwtConfiguration.getHeader().getName());

    if (header == null || !header.startsWith(jwtConfiguration.getHeader().getPrefix())) {
      chain.doFilter(request, response);
      return;
    }

    String token = header.replace(jwtConfiguration.getHeader().getPrefix(), "").trim();
    SecurityContextUtil.setSecurityContext(
        equalsIgnoreCase("signed", jwtConfiguration.getType()) ?
            validate(token) : decryptValidating(token));
    chain.doFilter(request, response);
  }

  @SneakyThrows
  private SignedJWT decryptValidating(String encryptedToken) {
    String signedToken = tokenConverter.decryptToken(encryptedToken);
    tokenConverter.validateTokenSignature(signedToken);
    return SignedJWT.parse(signedToken);
  }

  @SneakyThrows
  private SignedJWT validate(String signedToken) {
    tokenConverter.validateTokenSignature(signedToken);
    return SignedJWT.parse(signedToken);
  }
}