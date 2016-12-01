package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class PlayerCreationResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private PlayerDTO playerDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public PlayerDTO getPlayerDTO() {
    return playerDTO;
  }

  public void setPlayerDTO(PlayerDTO playerDTO) {
    this.playerDTO = playerDTO;
  }



}
