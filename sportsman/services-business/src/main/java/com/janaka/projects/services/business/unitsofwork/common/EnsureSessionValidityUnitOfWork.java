package com.janaka.projects.services.business.unitsofwork.common;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CustomAccountStatusUserDetailsChecker;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.common.CacheService;

public final class EnsureSessionValidityUnitOfWork extends UnitOfWork {

  public EnsureSessionValidityUnitOfWork(JmxNotificationPublisher jmxNotificationPublisher, CacheService cacheService) {
    super(jmxNotificationPublisher);
    this.cacheService = cacheService;
  }


  private User user = null;
  private Session session = null;

  private final CustomAccountStatusUserDetailsChecker detailsChecker = new CustomAccountStatusUserDetailsChecker();

  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

  private CacheService cacheService;

  @Override
  @Transactional(readOnly = true)
  protected void doWork() {
    // TODO: check in cache and return session if 1) existent 2) non-expired 3) user is still valid

    // if 1) existent
    session = cacheService.getFromCache(getSecurityContext().getToken(), Session.class);
    // System.out.println("ENSURING SESSION VALIDITY : " + session.getAuthorities());

    if (!(session == null)) {
      // 2) non-expired
      Date lastAccessedTime = session.getLastRequestTimestamp();
      Date now = Calendar.getInstance().getTime();
      long diff = now.getTime() - lastAccessedTime.getTime();
      System.out.println("diff :" + diff);
      long expiryPeriod = session.getExpires();
      System.out.println("expiryPeriod :" + expiryPeriod);
      if (!(diff > expiryPeriod)) {
        User userFromCache = cacheService.getFromCache(session.getName(), User.class);
        System.out.println("userFromCache " + userFromCache);

        detailsChecker.check(userFromCache);
      } else {
        // user expired
        cacheService.deleteFromCache(getSecurityContext().getToken());
        session = null;
        throw new AccountExpiredException(
            messages.getMessage("AccountStatusUserDetailsChecker.expired", "User account has expired"));
      }
    } else {
      // invalid token
      throw new BadCredentialsException("Invalid Token");
    }

    session.setLastRequestTimestamp(Calendar.getInstance().getTime());
    cacheService.updateCache(getSecurityContext().getToken(), session);
  }


  public User getUser() {
    return user;
  }


  public Session getSession() {
    return session;
  }



}
