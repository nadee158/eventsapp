package com.janaka.projects.dtos.responses.usermanagement;

import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class SecurityUserUpdateResponse extends BaseResponse {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private SecurityUserDTO securityUserDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public SecurityUserDTO getSecurityUserDTO() {
    return securityUserDTO;
  }

  public void setSecurityUserDTO(SecurityUserDTO securityUserDTO) {
    this.securityUserDTO = securityUserDTO;
  }



}
