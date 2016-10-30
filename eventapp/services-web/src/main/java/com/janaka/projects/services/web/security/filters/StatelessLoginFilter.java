package com.janaka.projects.services.web.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.services.web.security.RestAuthenticationFailureHandler;
import com.janaka.projects.services.web.security.RestAuthenticationSuccessHandler;
import com.janaka.projects.services.web.security.utils.TokenAuthenticationHandler;

public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {



  public StatelessLoginFilter(String urlMapping,
      TokenAuthenticationHandler tokenAuthenticationService, AuthenticationManager authManager) {
    super(new AntPathRequestMatcher(urlMapping));
    setAuthenticationManager(authManager);
    setAuthenticationSuccessHandler(
        new RestAuthenticationSuccessHandler(tokenAuthenticationService));
    setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

    Session session = null;

    final User user = new ObjectMapper().readValue(request.getInputStream(), User.class);


    final UsernamePasswordAuthenticationToken loginToken =
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());


    session = (Session) getAuthenticationManager().authenticate(loginToken);


    return session;
  }



  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    super.doFilter(req, res, chain);
  }

}
