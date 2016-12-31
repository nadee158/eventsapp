package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.TeamDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.TeamResponse;

public interface TeamService {

  public TeamResponse createTeam(TeamDTO request);

  public TeamResponse updateTeam(TeamDTO request);

  public ObjectDeletionResponse deleteTeam(ObjectDeletionRequest request);

  public ObjectListResponse<TeamDTO> getAllActiveTeams();

  public ObjectRetrievalResponse<TeamDTO> getTeamById(ObjectRetrievalRequest request);

  public TabularDataResponseModel<TeamDTO> getTeams(TabularDataRequestModel request);
}
