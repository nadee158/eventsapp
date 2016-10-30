package com.janaka.projects.services.business.unitsofwork.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.dtos.requests.common.SignUpRequest;
import com.janaka.projects.dtos.responses.common.SignUpReponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;


public final class SignUpUnitOfWork extends UnitOfWork {


  public SignUpUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher) {
    super(giveJmxNotificationPublisher);
  }

  private SecurityUserRepository repository;

  private SignUpRequest request = null;
  private SignUpReponse reponse = null;
  private SecurityUser user = null;

  public SignUpReponse getReponse() {
    return reponse;
  }

  public void setReponse(SignUpReponse reponse) {
    this.reponse = reponse;
  }

  public SignUpRequest getRequest() {
    return request;
  }

  public void setRequest(SignUpRequest request) {
    this.request = request;
  }

  public SecurityUserRepository getRepository() {
    return repository;
  }

  public void setRepository(SecurityUserRepository repository) {
    this.repository = repository;
  }

  public SecurityUser getUser() {
    return user;
  }

  public void setUser(SecurityUser user) {
    this.user = user;
  }

  protected void preExecute() {
    user = new SecurityUser(this.request.getUserName(), new BCryptPasswordEncoder().encode(this.request.getSecret()));
    Person person = new Person();
    person.setEmail(request.getEmail());
    person.setFullName(request.getFirstName());
    user.setPerson(person);
    user.setAccountEnabled(true);
    user.setAccountExpired(false);
    user.setAccountLocked(false);
    user.setCredentialsExpired(false);

    // Set<UserRole> roles = new HashSet<UserRole>();
    // roles.add(UserRole.USER);
    // user.setRoles(roles);


  }

  @Override
  @Transactional(readOnly = false)
  protected void doWork() {
    user = repository.save(user);
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.reponse = new SignUpReponse();
    if (isSuccessful) {
      reponse.setId(user.getId());
    }
    super.postExecute(isSuccessful);
  }

}
