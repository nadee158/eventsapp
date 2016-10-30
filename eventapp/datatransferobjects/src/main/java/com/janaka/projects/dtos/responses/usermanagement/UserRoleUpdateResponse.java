package com.janaka.projects.dtos.responses.usermanagement;

import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class UserRoleUpdateResponse extends BaseResponse {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id = 0;

  private UserRoleDTO userRoleDTO = null;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UserRoleDTO getUserRoleDTO() {
    return userRoleDTO;
  }

  public void setUserRoleDTO(UserRoleDTO userRoleDTO) {
    this.userRoleDTO = userRoleDTO;
  }



}
