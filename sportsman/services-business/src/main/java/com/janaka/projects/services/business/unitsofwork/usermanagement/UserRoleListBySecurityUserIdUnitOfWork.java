package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleListRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.SecurityUserRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleListBySecurityUserIdUnitOfWork extends UnitOfWork {

  private SecurityUserRepository securityUserRepository = null;

  private ObjectListResponse<UserRoleDTO> response = null;
  private List<UserRole> domainList = null;

  private UserRoleListRetrievalRequest request = null;

  private SecurityUser securityUser = null;

  @Override
  protected void preExecute() {
    if (!(request == null || request.getSecurityUserId() == 0)) {
      securityUser = securityUserRepository.findOne(request.getSecurityUserId());
    }
    // System.out.println("securityUser :" + securityUser);
    // System.out.println("securityUser.getUserRoles() :" + securityUser.getUserRoles());
    super.preExecute();
  }

  @Override
  protected void doWork() {
    if (!(securityUser == null || securityUser.getUserRoles() == null)) {
      this.domainList = new ArrayList<UserRole>();
      for (UserRole userRole : securityUser.getUserRoles()) {
        domainList.add(userRole);
      }
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new ObjectListResponse<UserRoleDTO>();
    if (isSuccessful) {
      if (!(this.domainList == null || this.domainList.isEmpty())) {
        List<UserRoleDTO> dtoList = new ArrayList<>();
        for (UserRole domain : this.domainList) {
          dtoList.add(UserRoleDTOConverter.convertDomainToDTO(domain));
        }
        this.response.setDtoList(dtoList);
        this.response.setListSize(dtoList.size());
      }

    }
    super.postExecute(isSuccessful);
  }

  public ObjectListResponse<UserRoleDTO> getResponse() {
    return response;
  }

  public UserRoleListBySecurityUserIdUnitOfWork(SecurityUserRepository securityUserRepository,
      UserRoleListRetrievalRequest request, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.securityUserRepository = securityUserRepository;
    this.request = request;
  }



}
