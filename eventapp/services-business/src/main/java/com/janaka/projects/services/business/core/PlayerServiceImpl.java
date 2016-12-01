package com.janaka.projects.services.business.core;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.core.PlayerService;

@Service("playerService")
@Transactional()
public class PlayerServiceImpl extends BusinessService implements PlayerService {

  @Override
  public TabularDataResponseModel<PlayerDTO> getPlayers(TabularDataRequestModel request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectRetrievalResponse<PlayerDTO> getPlayerById(ObjectRetrievalRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectListResponse<PlayerDTO> getAllActivePlayers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectDeletionResponse deletePlayer(ObjectDeletionRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PlayerUpdateResponse updatePlayer(PlayerUpdateRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PlayerCreationResponse createPlayer(PlayerCreationRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

}
