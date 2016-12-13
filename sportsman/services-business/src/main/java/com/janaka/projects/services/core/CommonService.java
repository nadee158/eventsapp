package com.janaka.projects.services.core;

import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.enums.Gender;

public interface CommonService {


  public ObjectListResponse<Gender> getGenderList();

}
