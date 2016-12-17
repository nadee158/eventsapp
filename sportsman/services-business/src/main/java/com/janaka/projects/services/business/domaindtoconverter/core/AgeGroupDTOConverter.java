package com.janaka.projects.services.business.domaindtoconverter.core;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.core.AgeGroupDTO;
import com.janaka.projects.dtos.requests.core.AgeGroupCreationRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupUpdateRequest;
import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class AgeGroupDTOConverter {

  public static AgeGroup convertRequestToDomain(AgeGroupCreationRequest request) {
    if (!(request == null)) {
      AgeGroup ageGroup = new AgeGroup();
      ageGroup.setFromAge(request.getFromAge());
      ageGroup.setToAge(request.getToAge());
      return ageGroup;
    }
    return null;
  }

  public static AgeGroupDTO convertDomainToDTO(AgeGroup ageGroup) {
    if (!(ageGroup == null)) {
      AgeGroupDTO dto = new AgeGroupDTO();
      dto.setToAge(ageGroup.getToAge());
      dto.setFromAge(ageGroup.getFromAge());
      dto.setVersion(ageGroup.getVersion());
      dto.setId(ageGroup.getId());
      dto.setRecordStatus(ageGroup.getRecordStatus().toString());
      return dto;
    }
    return null;
  }

  public static AgeGroup updateDomainFromRequest(AgeGroupUpdateRequest request, AgeGroup ageGroupFromDb) {
    if (!(ageGroupFromDb == null || request == null)) {
      ageGroupFromDb.setFromAge(request.getFromAge());
      ageGroupFromDb.setToAge(request.getToAge());
      if (StringUtils.isNoneEmpty(request.getRecordStatus())) {
        ageGroupFromDb.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      }
    }
    return ageGroupFromDb;
  }

}
