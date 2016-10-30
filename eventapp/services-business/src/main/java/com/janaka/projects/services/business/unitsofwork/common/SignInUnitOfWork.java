package com.janaka.projects.services.business.unitsofwork.common;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.SignInRequest;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public final class SignInUnitOfWork extends UnitOfWork {

  public SignInUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher) {
    super(giveJmxNotificationPublisher);
  }

  SecurityUserRepository repository;


  private SignInRequest request = null;
  private User user = null;

  public SignInRequest getRequest() {
    return this.request;
  }

  public void setRequest(SignInRequest request) {
    this.request = request;
  }

  public SecurityUserRepository getRepository() {
    return repository;
  }

  public void setRepository(SecurityUserRepository repository) {
    this.repository = repository;
  }


  @Override
  @Transactional(readOnly = true)
  protected void doWork() {

    SecurityUser securityUser = repository.findByUserName(request.getUserName());

    if (securityUser == null) {
      throw new UsernameNotFoundException("user not found");
    }

    user = securityUser.constructUser();
    System.out.println("SignInUnitOfWork " + user);
  }

  public User getUser() {
    return user;
  }



}
