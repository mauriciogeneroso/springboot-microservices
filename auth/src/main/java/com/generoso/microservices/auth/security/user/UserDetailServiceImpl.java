package com.generoso.microservices.auth.security.user;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.generoso.microservices.core.model.ApplicationUser;
import com.generoso.microservices.core.repository.ApplicationUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Mauricio Generoso
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailServiceImpl implements UserDetailsService {

  private final ApplicationUserRepository applicationUserRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    log.info("Searching in database the user by username {}", username);
    ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

    if (applicationUser == null)
      throw  new UsernameNotFoundException(String.format("Application user %s not found", username));

    return new CustomUserDetail(applicationUser);
  }

  private static final class CustomUserDetail extends ApplicationUser implements UserDetails {

    public CustomUserDetail(ApplicationUser applicationUser) {
      super(applicationUser);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + this.getRole());
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }

}
