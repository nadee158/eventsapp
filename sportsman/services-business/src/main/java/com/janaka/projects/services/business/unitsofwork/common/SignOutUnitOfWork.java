package com.janaka.projects.services.business.unitsofwork.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.dtos.requests.common.SignOutRequest;
import com.janaka.projects.dtos.responses.common.SignOutResponse;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.common.CacheService;

public final class SignOutUnitOfWork extends UnitOfWork {

  public SignOutUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher, CacheService cacheService) {
    super(giveJmxNotificationPublisher);
    this.cacheService = cacheService;
  }

  private Logger logger = Logger.getLogger(this.getClass());

  private SignOutResponse reponse = null;
  private SignOutRequest request = null;
  private CacheService cacheService;

  @Override
  @Transactional(readOnly = true)
  protected void doWork() {
    reponse = new SignOutResponse();
    System.out.println(reponse);
    System.out.println(request);
    System.out.println("getSecurityContext().getToken() " + getSecurityContext().getToken());
    reponse.setStatusCode(ApplicationConstants.STATUS_CODE_NO_CONTENT);
    // if 1) existent
    Session session = cacheService.getFromCache(getSecurityContext().getToken(), Session.class);
    System.out.println(session);
    if (!(session == null)) {
      if (!(session.getOwnerId() == null || request.getSecurityUserId() == null)) {
        if (StringUtils.equals(session.getOwnerId().toString(), request.getSecurityUserId().toString())) {
          System.out.println("Removing User");
          cacheService.deleteFromCache(getSecurityContext().getToken());
          session = null;
          reponse.setMessage("Successfully signed out!");
          reponse.setStatusCode(ApplicationConstants.STATUS_CODE_OK);
        } else {
          reponse.setMessage("Invalid request to log out!");
        }
      } else {
        reponse.setMessage("Invalid request to log out!");
      }
    } else {
      reponse.setMessage("Already user has logged out!");
    }

  }

  public SignOutResponse getReponse() {
    return reponse;
  }

  public void setReponse(SignOutResponse reponse) {
    this.reponse = reponse;
  }

  public SignOutRequest getRequest() {
    return request;
  }

  public void setRequest(SignOutRequest request) {
    this.request = request;
  }



}
