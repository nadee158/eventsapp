package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.specifications.usermanagement.SecurityUserSpecifications;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.SecurityUserDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class SecurityUserDataTablesOutputUnitOfWork extends UnitOfWork {

  private SecurityUserRepository repository = null;

  private TabularDataRequestModel request = null;
  private TabularDataResponseModel<SecurityUserDTO> response = null;

  private DataTablesOutput<SecurityUser> domainResponse = null;

  private SecurityService securityService = null;

  private long securityUserId = 0;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private User userFromSession = null;

  @Override
  protected void preExecute() {
    setAuditContext(auditContext);
    setSecurityContext(securityContext);
    if (!(securityContext == null || securityContext.getToken() == null)) {
      GetSessionDetailsRequest getSessionDetailsRequest = new GetSessionDetailsRequest();
      getSessionDetailsRequest.setToken(securityContext.getToken());
      GetSessionDetailsResponse getSessionDetailsResponse = securityService.getSessionDetails(getSessionDetailsRequest);
      if (!(getSessionDetailsResponse == null)) {
        userFromSession = getSessionDetailsResponse.getUser();
      }
    }

    if (!(userFromSession == null)) {
      this.securityUserId = userFromSession.getId();
    }

    super.preExecute();
  }

  @Override
  protected void doWork() {
    this.domainResponse = repository.findAll(request, SecurityUserSpecifications.isNotDeleted(securityUserId));
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new TabularDataResponseModel<SecurityUserDTO>();
    if (isSuccessful) {
      if (!(this.domainResponse == null)) {
        if (!(this.domainResponse.getData() == null || this.domainResponse.getData().isEmpty())) {
          List<SecurityUserDTO> dtoList = new ArrayList<>();
          for (SecurityUser domain : this.domainResponse.getData()) {
            dtoList.add(SecurityUserDTOConverter.convertDomainToDTO(domain));
          }
          this.response.setData(dtoList);
        }
        this.response.setDraw(this.domainResponse.getDraw());
        this.response.setError(this.domainResponse.getError());
        this.response.setRecordsFiltered(this.domainResponse.getRecordsFiltered());
        this.response.setRecordsTotal(this.domainResponse.getRecordsTotal());
      }
    }
    super.postExecute(isSuccessful);
  }



  public SecurityUserDataTablesOutputUnitOfWork(SecurityUserRepository repository, TabularDataRequestModel request,
      SecurityService securityService, AuditContext auditContext, SecurityContext securityContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = repository;
    this.request = request;
    this.securityService = securityService;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public TabularDataResponseModel<SecurityUserDTO> getResponse() {
    return response;
  }



}
