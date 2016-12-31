package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.GradeBeltDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class GradeBeltResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private GradeBeltDTO gradeBeltDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public GradeBeltDTO getGradeBeltDTO() {
    return gradeBeltDTO;
  }

  public void setGradeBeltDTO(GradeBeltDTO gradeBeltDTO) {
    this.gradeBeltDTO = gradeBeltDTO;
  }

  @Override
  public String toString() {
    return "GradeBeltResponse [id=" + id + ", gradeBeltDTO=" + gradeBeltDTO + "]";
  }



}
