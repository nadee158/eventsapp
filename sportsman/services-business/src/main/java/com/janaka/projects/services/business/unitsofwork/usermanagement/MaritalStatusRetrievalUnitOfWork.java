package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.domain.usermanagement.MaritalStatusDTO;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.enums.MaritalStatus;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;


public class MaritalStatusRetrievalUnitOfWork extends UnitOfWork {

  private ObjectListResponse<MaritalStatusDTO> response = null;
  private List<MaritalStatusDTO> maritalStatusList = null;

  @Override
  protected void doWork() {
    maritalStatusList = new ArrayList<>();
    for (int i = 0; i < MaritalStatus.values().length; i++) {
      MaritalStatus maritalStatus = MaritalStatus.values()[i];
      maritalStatusList.add(new MaritalStatusDTO(maritalStatus.getCode(), maritalStatus.getName()));
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectListResponse<MaritalStatusDTO>();
    if (isSuccessful) {
      response.setDtoList(maritalStatusList);
    }
    super.postExecute(isSuccessful);
  }

  public MaritalStatusRetrievalUnitOfWork(JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
  }

  public ObjectListResponse<MaritalStatusDTO> getResponse() {
    return response;
  }
}
