package com.janaka.projects.services.business.unitsofwork.usermanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.domain.usermanagement.PrefixDTO;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.enums.Prefix;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;


public class PrefixRetrievalUnitOfWork extends UnitOfWork {


  private ObjectListResponse<PrefixDTO> response = null;

  private List<PrefixDTO> prefixes = null;

  @Override
  protected void doWork() {
    prefixes = new ArrayList<>();
    for (int i = 0; i < Prefix.values().length; i++) {
      Prefix prefix = Prefix.values()[i];
      prefixes.add(new PrefixDTO(prefix.getCode(), prefix.getName()));
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    response = new ObjectListResponse<PrefixDTO>();
    if (isSuccessful) {
      response.setDtoList(prefixes);
    }
    super.postExecute(isSuccessful);
  }

  public PrefixRetrievalUnitOfWork(JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
  }

  public ObjectListResponse<PrefixDTO> getResponse() {
    return response;
  }



}
