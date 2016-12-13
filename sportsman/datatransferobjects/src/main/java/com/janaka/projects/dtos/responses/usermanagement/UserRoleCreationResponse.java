package com.janaka.projects.dtos.responses.usermanagement;

import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class UserRoleCreationResponse extends BaseResponse {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private UserRoleDTO userRoleDTO;

  public UserRoleDTO getUserRoleDTO() {
    return userRoleDTO;
  }

  public void setUserRoleDTO(UserRoleDTO userRoleDTO) {
    this.userRoleDTO = userRoleDTO;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }



}
