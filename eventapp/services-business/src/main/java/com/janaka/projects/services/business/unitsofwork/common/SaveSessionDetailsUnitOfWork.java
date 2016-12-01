package com.janaka.projects.services.business.unitsofwork.common;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.SaveSessionDetailsRequest;
import com.janaka.projects.dtos.responses.common.SaveSessionDetailsResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.common.CacheService;

public final class SaveSessionDetailsUnitOfWork extends UnitOfWork {

  public SaveSessionDetailsUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher,
      CacheService cacheService) {
    super(giveJmxNotificationPublisher);
    this.cacheService = cacheService;
  }

  private SecurityUserRepository repository;

  private SaveSessionDetailsRequest request = null;
  private SaveSessionDetailsResponse response = null;

  private CacheService cacheService;

  private User user = null;
  private Session session = null;

  public SecurityUserRepository getRepository() {
    return repository;
  }

  public void setRepository(SecurityUserRepository repository) {
    this.repository = repository;
  }

  @Override
  protected void preExecute() {
    session = request.getSession();
    SecurityUser securityUser = repository.findByUuId(session.getOwnerId().toString());
    // construct user
    user = securityUser.constructUser();
    // get list of organizations user has access to

  }


  @Override
  @Transactional(readOnly = true)
  protected void doWork() {
    // add session to cache
    cacheService.addToCache(session.getToken(), session);

    // add user to cache
    User userFromCache = cacheService.getFromCache(session.getName(), User.class);
    if (userFromCache == null) {
      userFromCache = user;
    }

    if (userFromCache.getActiveSessions() == null) {
      userFromCache.setActiveSessions(new ArrayList<String>());
    }

    userFromCache.getActiveSessions().add(session.getId().toString());
    userFromCache.setCustomActiveSessionCount(userFromCache.getCustomActiveSessionCount() + 1);

    cacheService.addToCache(session.getName(), userFromCache);

    response = new SaveSessionDetailsResponse();
    response.setUser(user);

  }

  public SaveSessionDetailsRequest getRequest() {
    return request;
  }

  public void setRequest(SaveSessionDetailsRequest request) {
    this.request = request;
  }

  public SaveSessionDetailsResponse getResponse() {
    return response;
  }

  public void setResponse(SaveSessionDetailsResponse response) {
    this.response = response;
  }

}
