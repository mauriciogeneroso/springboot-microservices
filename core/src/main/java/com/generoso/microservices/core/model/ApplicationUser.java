package com.generoso.microservices.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mauricio Generoso
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApplicationUser implements AbstractEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @Column(nullable = false)
  private String username;

  @ToString.Exclude
  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role = "USER";

  public ApplicationUser(ApplicationUser applicationUser) {
    this.id = applicationUser.getId();
    this.username = applicationUser.getUsername();
    this.password = applicationUser.getPassword();
    this.role = applicationUser.getRole();
  }

  @Override
  public Long getId() {
    return id;
  }
}
