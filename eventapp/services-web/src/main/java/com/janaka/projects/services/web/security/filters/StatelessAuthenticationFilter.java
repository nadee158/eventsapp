package com.janaka.projects.services.web.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.janaka.projects.common.security.SecurityHelper;
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
    SecurityContextHolder.getContext()
        .setAuthentication(tokenAuthenticationHandler.getAuthentication((HttpServletRequest) req));
    chain.doFilter(req, res); // always continue
  }
}
