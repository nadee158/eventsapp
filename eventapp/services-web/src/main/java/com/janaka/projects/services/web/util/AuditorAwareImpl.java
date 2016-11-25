package com.janaka.projects.services.web.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {


  @Override
  public String getCurrentAuditor() {
    String userName = "UNKNOWN";
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    System.out.println("authentication " + authentication);
    if (authentication == null) {
      userName = "ANNONYMOUS_USER";
    } else {
      if (!authentication.isAuthenticated()) {
        userName = "UNAUTHORIZED_USER";
      }
      if (!(authentication.getPrincipal() == null)) {
        userName = (String) authentication.getName();
      }
    }
    System.out.println("LOGGED IN USER NAME " + userName);
    return userName;
  }



}
