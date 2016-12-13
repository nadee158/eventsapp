package com.janaka.projects.services.reports.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    System.out.println("LOGIN SUCCESS");
    // otherwise send to the default location from front end
    target.onAuthenticationSuccess(request, response, authentication);


  }



}
