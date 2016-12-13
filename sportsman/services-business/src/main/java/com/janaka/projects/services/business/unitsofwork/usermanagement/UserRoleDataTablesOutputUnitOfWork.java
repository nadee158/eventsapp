package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.entitymanagement.specifications.usermanagement.UserRoleSpecifications;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.usermanagement.UserRoleDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class UserRoleDataTablesOutputUnitOfWork extends UnitOfWork {

  private UserRoleRepository repository = null;

  private TabularDataRequestModel request = null;
  private TabularDataResponseModel<UserRoleDTO> response = null;

  private DataTablesOutput<UserRole> domainResponse = null;


  @Override
  protected void doWork() {
    this.domainResponse = repository.findAll(request, UserRoleSpecifications.isNotDeleted());
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    this.response = new TabularDataResponseModel<UserRoleDTO>();
    if (isSuccessful) {
      if (!(this.domainResponse == null)) {
        if (!(this.domainResponse.getData() == null || this.domainResponse.getData().isEmpty())) {
          List<UserRoleDTO> dtoList = new ArrayList<>();
          for (UserRole domain : this.domainResponse.getData()) {
            dtoList.add(UserRoleDTOConverter.convertDomainToDTO(domain));
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

  public UserRoleDataTablesOutputUnitOfWork(UserRoleRepository userRoleRepository, TabularDataRequestModel request,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.repository = userRoleRepository;
    this.request = request;
  }

  public TabularDataResponseModel<UserRoleDTO> getResponse() {
    return response;
  }



}
