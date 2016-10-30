package com.janaka.projects.services.business.unitsofwork.common;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.caching.Cache;
import com.janaka.projects.common.caching.CacheFactory;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public final class EnsureSessionValidityUnitOfWork extends UnitOfWork {

  public EnsureSessionValidityUnitOfWork(JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
  }


  private User user = null;
  private Session session = null;

  private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();


  @Override
  @Transactional(readOnly = true)
  protected void doWork() {
    // TODO: check in cache and return session if 1) existent 2) non-expired 3) user is still valid

    Cache cache = CacheFactory.getCache();

    // if 1) existent
    session = cache.get(getSecurityContext().getToken());
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
        User userFromCache = cache.get(session.getName());
        detailsChecker.check(userFromCache);
      } else {
        // user expired
        cache.remove(getSecurityContext().getToken());
        session = null;
        throw new AccountExpiredException(
            messages.getMessage("AccountStatusUserDetailsChecker.expired", "User account has expired"));
      }
    } else {
      // invalid token
      throw new BadCredentialsException("Invalid Token");
    }

    session.setLastRequestTimestamp(Calendar.getInstance().getTime());
    cache.set(getSecurityContext().getToken(), session);
  }


  public User getUser() {
    return user;
  }


  public Session getSession() {
    return session;
  }



}
