package com.janaka.projects.services.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.responses.common.SignInResponse;
import com.janaka.projects.services.web.security.utils.SecurityUtils;
import com.janaka.projects.services.web.security.utils.TokenAuthenticationHandler;

/**
 * Spring Security success handler, specialized for Ajax requests.
 */
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private TokenAuthenticationHandler tokenAuthenticationHandler;



  public RestAuthenticationSuccessHandler(TokenAuthenticationHandler tokenAuthenticationHandler) {
    super();
    this.tokenAuthenticationHandler = tokenAuthenticationHandler;
  }



  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {

    User user = tokenAuthenticationHandler.addAuthentication(response, authentication);

    // Add the authentication to the Security context
    SecurityContextHolder.getContext().setAuthentication(authentication);
    System.out.println("AUTH AFTER LOGIN :" + authentication.getAuthorities());

    SignInResponse signInResponse = new SignInResponse(user);

    SecurityUtils.sendResponse(response, HttpServletResponse.SC_OK, signInResponse);
  }


}
