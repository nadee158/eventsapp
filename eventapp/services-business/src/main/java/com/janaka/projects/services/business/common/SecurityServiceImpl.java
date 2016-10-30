package com.janaka.projects.services.business.common;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.requests.common.ChangePasswordRequest;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.ResetPasswordRequest;
import com.janaka.projects.dtos.requests.common.SaveSessionDetailsRequest;
import com.janaka.projects.dtos.requests.common.SignInRequest;
import com.janaka.projects.dtos.requests.common.SignOutRequest;
import com.janaka.projects.dtos.requests.common.SignUpRequest;
import com.janaka.projects.dtos.responses.common.ChangePasswordReponse;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.common.ResetPasswordReponse;
import com.janaka.projects.dtos.responses.common.SaveSessionDetailsResponse;
import com.janaka.projects.dtos.responses.common.SignOutResponse;
import com.janaka.projects.dtos.responses.common.SignUpReponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.services.business.unitsofwork.common.ChangePasswordUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.EnsureSessionValidityUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.GetSessionDetailsUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.ResetPasswordUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SaveSessionDetailsUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SignInUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SignOutUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.common.SignUpUnitOfWork;
import com.janaka.projects.services.common.NotificationService;
import com.janaka.projects.services.common.SecurityService;

@Service(value = "securityService")
@Transactional
public class SecurityServiceImpl extends BusinessService implements SecurityService {

  @Autowired
  private SecurityUserRepository repository;

  @Autowired
  private NotificationService notificationService;

  @Autowired
  private JmxNotificationPublisher jmxNotificationPublisher;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("username :" + username);

    SignInUnitOfWork uow = new SignInUnitOfWork(jmxNotificationPublisher);

    SignInRequest request = new SignInRequest();
    request.setUserName(username);

    // set data on unit of work
    uow.setRequest(request);
    uow.setRepository(repository);

    // execute unit of work
    this.doWork(uow);

    // get result data
    User user = uow.getUser();
    System.out.println("response 99 :" + user);
    return user;

  }



  @Override
  public SignOutResponse signOut(SecurityContext securityContext, AuditContext auditContext, SignOutRequest request) {
    SignOutUnitOfWork uow = new SignOutUnitOfWork(jmxNotificationPublisher);
    uow.setSecurityContext(securityContext);
    uow.setRequest(request);
    // set infrastructure settings
    uow.setAuditContext(auditContext);

    // execute unit of work
    this.doWork(uow);

    return uow.getReponse();
  }

  @Override
  public SignUpReponse signUp(SecurityContext securityContext, AuditContext auditContext, SignUpRequest request) {
    SignUpUnitOfWork uow = new SignUpUnitOfWork(jmxNotificationPublisher);
    uow.setRequest(request);
    uow.setAuditContext(auditContext);
    uow.setRepository(repository);
    this.doWork(uow);
    return uow.getReponse();
  }

  @Override
  public ResetPasswordReponse resetPassword(SecurityContext securityContext, AuditContext auditContext,
      ResetPasswordRequest request) {
    ResetPasswordUnitOfWork uow = new ResetPasswordUnitOfWork(notificationService, repository, request, securityContext,
        auditContext, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }


  @Override
  public ChangePasswordReponse changePassword(SecurityContext securityContext, AuditContext auditContext,
      ChangePasswordRequest request) {
    ChangePasswordUnitOfWork uow = new ChangePasswordUnitOfWork(notificationService, repository, request,
        securityContext, auditContext, this, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }

  @Override
  public Session ensureSessionValidity(SecurityContext securityContext, AuditContext auditContext) {
    // TODO: check in cache and return session if 1) existent 2) non-expired 3) user is still valid
    EnsureSessionValidityUnitOfWork uow = new EnsureSessionValidityUnitOfWork(jmxNotificationPublisher);
    uow.setAuditContext(auditContext);
    uow.setSecurityContext(securityContext);
    this.doWork(uow);
    return uow.getSession();
  }



  @Override
  public SaveSessionDetailsResponse saveSessionDetails(SaveSessionDetailsRequest saveSessionDetailsRequest) {
    SaveSessionDetailsUnitOfWork uow = new SaveSessionDetailsUnitOfWork(jmxNotificationPublisher);
    uow.setRepository(repository);
    uow.setRequest(saveSessionDetailsRequest);
    this.doWork(uow);
    return uow.getResponse();
  }



  @Override
  public GetSessionDetailsResponse getSessionDetails(GetSessionDetailsRequest request) {
    GetSessionDetailsUnitOfWork uow = new GetSessionDetailsUnitOfWork(request, jmxNotificationPublisher);
    this.doWork(uow);
    return uow.getResponse();
  }


}
