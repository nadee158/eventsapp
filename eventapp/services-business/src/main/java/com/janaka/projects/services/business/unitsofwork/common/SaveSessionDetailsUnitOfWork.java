package com.janaka.projects.services.business.unitsofwork.common;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.caching.Cache;
import com.janaka.projects.common.caching.CacheFactory;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.SaveSessionDetailsRequest;
import com.janaka.projects.dtos.responses.common.SaveSessionDetailsResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public final class SaveSessionDetailsUnitOfWork extends UnitOfWork {

  public SaveSessionDetailsUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher) {
    super(giveJmxNotificationPublisher);
  }

  private SecurityUserRepository repository;

  private SaveSessionDetailsRequest request = null;
  private SaveSessionDetailsResponse response = null;

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
    Cache cache = CacheFactory.getCache();

    // add session to cache
    cache.set(session.getToken(), session);

    // add user to cache
    User userFromCache = cache.get(session.getName());
    if (userFromCache == null) {
      userFromCache = user;
    }

    if (userFromCache.getActiveSessions() == null) {
      userFromCache.setActiveSessions(new ArrayList<String>());
    }

    userFromCache.getActiveSessions().add(session.getId().toString());
    userFromCache.setActiveSessionCount(userFromCache.getActiveSessionCount() + 1);

    cache.set(session.getName(), userFromCache);

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
