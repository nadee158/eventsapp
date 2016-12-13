package com.janaka.projects.services.reports.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response, AuthenticationException arg2)
      throws IOException, ServletException {
    System.out.println("LOGIN FAILED");
    response.sendRedirect("/login?error=1");
  }

}
