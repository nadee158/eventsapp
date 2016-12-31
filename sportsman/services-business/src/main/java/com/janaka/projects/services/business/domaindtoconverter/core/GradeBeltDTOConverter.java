package com.janaka.projects.services.business.domaindtoconverter.core;

import com.janaka.projects.dtos.domain.core.GradeBeltDTO;
import com.janaka.projects.entitymanagement.domain.core.GradeBelt;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class GradeBeltDTOConverter {

  public static GradeBelt convertRequestToDomain(GradeBeltDTO request) {
    if (!(request == null)) {
      GradeBelt gradeBelt = new GradeBelt();
      gradeBelt.setGradeBeltName(request.getGradeBeltName());
      gradeBelt.setRecordStatus(RecordStatus.ACTIVE);
      return gradeBelt;
    }
    return null;
  }

  public static GradeBeltDTO convertDomainToDTO(GradeBelt gradeBelt) {
    if (!(gradeBelt == null)) {
      GradeBeltDTO gradeBeltDTO = new GradeBeltDTO();
      gradeBeltDTO.setId(gradeBelt.getId());
      gradeBeltDTO.setRecordStatus(gradeBelt.getRecordStatus().getRecordStatusCode());
      gradeBeltDTO.setGradeBeltName(gradeBelt.getGradeBeltName());
      gradeBeltDTO.setVersion(gradeBelt.getVersion());
      return gradeBeltDTO;
    }
    return null;
  }

  public static GradeBelt updateDomainFromRequest(GradeBeltDTO request, GradeBelt gradeBelt) {
    if (!(request == null || gradeBelt == null)) {
      gradeBelt.setGradeBeltName(request.getGradeBeltName());
      gradeBelt.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      return gradeBelt;
    }
    return null;
  }

}
