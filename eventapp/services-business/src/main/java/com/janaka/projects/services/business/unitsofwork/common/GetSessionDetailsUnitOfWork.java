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
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public final class GetSessionDetailsUnitOfWork extends UnitOfWork {

  private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();


  private GetSessionDetailsRequest request = null;
  private GetSessionDetailsResponse response = null;

  private Session session = null;
  private User userFromCache = null;


  @Override
  @Transactional(readOnly = true)
  protected void doWork() {

    Cache cache = CacheFactory.getCache();

    // if 1) existent
    session = cache.get(request.getToken());

    if (!(session == null)) {
      // 2) non-expired
      Date lastAccessedTime = session.getLastRequestTimestamp();
      Date now = Calendar.getInstance().getTime();
      long diff = now.getTime() - lastAccessedTime.getTime();
      System.out.println("diff :" + diff);
      long expiryPeriod = session.getExpires();
      System.out.println("expiryPeriod :" + expiryPeriod);
      if (!(diff > expiryPeriod)) {
        userFromCache = cache.get(session.getName());
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

  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    if (isSuccessful) {
      this.response = new GetSessionDetailsResponse();
      response.setSession(session);
      response.setUser(userFromCache);
    }
    super.postExecute(isSuccessful);
  }


  public GetSessionDetailsUnitOfWork(GetSessionDetailsRequest request,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.request = request;
  }


  public GetSessionDetailsResponse getResponse() {
    return response;
  }



}
