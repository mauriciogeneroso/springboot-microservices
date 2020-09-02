package com.generoso.microservices.core.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mauricio Generoso
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfiguration {

  private String urlLogin = "/login/**";

  @NestedConfigurationProperty
  private Header header = new Header();

  private int expiration = 3600;
  private String privateKey = "qxBEEQv7E8aviX1KUcdOiF5ve5COUPAr";
  private String type = "encrypted";

  @Getter
  @Setter
  public static class Header {
    private String name = "Authorization";
    private String prefix = "Bearer ";
  }
}
