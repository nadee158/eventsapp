package com.janaka.projects.services.business.domaindtoconverter.core;

import com.janaka.projects.dtos.domain.core.TeamDTO;
import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class TeamDTOConverter {

  public static Team convertRequestToDomain(TeamDTO request) {
    if (!(request == null)) {
      Team team = new Team();
      team.setTeamName(request.getTeamName());
      team.setRecordStatus(RecordStatus.ACTIVE);
      return team;
    }
    return null;
  }

  public static TeamDTO convertDomainToDTO(Team team) {
    if (!(team == null)) {
      TeamDTO teamDto = new TeamDTO();
      teamDto.setId(team.getId());
      teamDto.setRecordStatus(team.getRecordStatus().getRecordStatusCode());
      teamDto.setTeamName(team.getTeamName());
      teamDto.setVersion(team.getVersion());
      return teamDto;
    }
    return null;
  }

  public static Team updateDomainFromRequest(TeamDTO request, Team team) {
    if (!(request == null || team == null)) {
      team.setTeamName(request.getTeamName());
      team.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      return team;
    }
    return null;
  }

}
