package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.TeamDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class TeamResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private TeamDTO teamDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public TeamDTO getTeamDTO() {
    return teamDTO;
  }

  public void setTeamDTO(TeamDTO teamDTO) {
    this.teamDTO = teamDTO;
  }

  @Override
  public String toString() {
    return "TeamResponse [id=" + id + ", teamDTO=" + teamDTO + "]";
  }



}
