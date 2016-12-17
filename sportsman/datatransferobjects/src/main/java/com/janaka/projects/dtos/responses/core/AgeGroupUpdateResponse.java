package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.AgeGroupDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class AgeGroupUpdateResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private AgeGroupDTO ageGroupDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public AgeGroupDTO getAgeGroupDTO() {
    return ageGroupDTO;
  }

  public void setAgeGroupDTO(AgeGroupDTO ageGroupDTO) {
    this.ageGroupDTO = ageGroupDTO;
  }



}
