package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.PlayerCreationRequest;
import com.janaka.projects.dtos.requests.core.PlayerUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.PlayerCreationResponse;
import com.janaka.projects.dtos.responses.core.PlayerUpdateResponse;

public interface PlayerService {

  public TabularDataResponseModel<PlayerDTO> getPlayers(TabularDataRequestModel request);

  public ObjectRetrievalResponse<PlayerDTO> getPlayerById(ObjectRetrievalRequest request);

  public ObjectListResponse<PlayerDTO> getAllActivePlayers();

  public ObjectDeletionResponse deletePlayer(ObjectDeletionRequest request);

  public PlayerUpdateResponse updatePlayer(PlayerUpdateRequest request);

  public PlayerCreationResponse createPlayer(PlayerCreationRequest request);

  public ObjectRetrievalResponse<PlayerDTO> getPlayerByIcPassport(ObjectRetrievalRequest request);

  public ObjectRetrievalResponse<PlayerDTO> getPlayerByPlayerNo(ObjectRetrievalRequest request);

}
