package com.janaka.projects.services.web.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.services.web.security.utils.SecurityUtils;
import com.janaka.projects.services.web.security.utils.TokenAuthenticationHandler;

public class StatelessAuthenticationFilter extends GenericFilterBean {

  private final TokenAuthenticationHandler tokenAuthenticationHandler;

  public StatelessAuthenticationFilter(TokenAuthenticationHandler taService) {
    this.tokenAuthenticationHandler = taService;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    System.out.println(SecurityHelper.getAuditContext());
    System.out.println(SecurityHelper.getSecurityContext());
    HttpServletResponse response = (HttpServletResponse) res;
    try {
      SecurityContextHolder.getContext()
          .setAuthentication(tokenAuthenticationHandler.getAuthentication((HttpServletRequest) req));
    } catch (BadCredentialsException e) {
      SecurityUtils.sendError(response, e, HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
      System.out.println("%%%%%%%%%%%%%%%%%%%%");
      e.printStackTrace();
    }

    chain.doFilter(req, res); // always continue
  }
}
