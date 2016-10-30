package com.janaka.projects.services.web.security.utils;

import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.services.common.SecurityService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private SecurityService securityService;

  private PasswordEncoder passwordEncoder;

  private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String username = (String) authentication.getPrincipal();
    String password = (String) authentication.getCredentials();

    // TODO: use security service to check user/secret credentials
    Object obj = securityService.loadUserByUsername(username);
    if (obj == null) {
      throw new BadCredentialsException("Username not found.");
    }

    User user = (User) obj;

    if (!(passwordEncoder.matches(password, user.getPassword()))) {
      throw new BadCredentialsException("Wrong password.");
    }

    // TODO: check validity of user account
    detailsChecker.check(user);


    // TODO: create Session object and return it
    return new Session(user);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
  }

  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public void setSecurityService(SecurityService securityService) {
    this.securityService = securityService;
  }



}
