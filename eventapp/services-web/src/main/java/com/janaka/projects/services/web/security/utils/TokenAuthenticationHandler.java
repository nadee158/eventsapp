package com.janaka.projects.services.web.security.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.SaveSessionDetailsRequest;
import com.janaka.projects.dtos.responses.common.SaveSessionDetailsResponse;
import com.janaka.projects.services.common.SecurityService;

@Component
public class TokenAuthenticationHandler {

  @Autowired
  private SecurityService securityService = null;

  private final TokenHandler tokenHandler;

  @Autowired
  public TokenAuthenticationHandler(@Value("${token.secret}") String secret) {
    tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
  }

  public User addAuthentication(HttpServletResponse response, Authentication authentication) {
    String token = tokenHandler.createTokenForUser((String) authentication.getDetails());
    response.addHeader(SecurityHelper.AUTH_HEADER_NAME, token);
    Session session = (Session) authentication;
    session.setToken(token);
    session.setAuthenticated(true);
    System.out.println();
    SaveSessionDetailsRequest saveSessionDetailsRequest = new SaveSessionDetailsRequest();
    saveSessionDetailsRequest.setSession(session);
    SaveSessionDetailsResponse saveSessionDetailsResponse =
        this.securityService.saveSessionDetails(saveSessionDetailsRequest);
    return saveSessionDetailsResponse.getUser();
  }

  public Authentication getAuthentication(HttpServletRequest request) {
    Authentication authentication = null;
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    final String token = securityContext.getToken();
    System.out.println("token :" + token);
    if (token != null) {

      AuditContext auditContext = SecurityHelper.getAuditContext();

      // call security service to validation and return session object
      authentication = this.securityService.ensureSessionValidity(securityContext, auditContext);

      boolean isValidToken = tokenHandler.isValidToken((String) authentication.getDetails(), token);
      System.out.println("isValidToken :" + isValidToken);
      System.out.println("AUTH WHEN CHEKING ||| :" + authentication.getAuthorities());
      // TODO: use exception handler if needed to catch security exception and return null to Spring
    }

    return authentication;
  }
}
