package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
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
import com.janaka.projects.entitymanagement.dataaccessobjects.core.CategorySetupRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.PersonRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.PlayerRepository;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.PlayerDTOConverter;
import com.janaka.projects.services.core.PlayerService;

@Service("playerService")
@Transactional()
public class PlayerServiceImpl extends BusinessService implements PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private CategorySetupRepository categorySetupRepository;


  @Override
  public TabularDataResponseModel<PlayerDTO> getPlayers(TabularDataRequestModel request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectRetrievalResponse<PlayerDTO> getPlayerById(ObjectRetrievalRequest request) {
    Player player = playerRepository.findOne(request.getId());
    if (!(player == null)) {
      ObjectRetrievalResponse<PlayerDTO> response = new ObjectRetrievalResponse<PlayerDTO>();
      response.setDto(PlayerDTOConverter.convertDomainToDTO(player));
      response.setId(player.getId());
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public ObjectListResponse<PlayerDTO> getAllActivePlayers() {
    List<Player> players = playerRepository.findByRecordStatus(RecordStatus.ACTIVE);
    if (!(players == null)) {
      ObjectListResponse<PlayerDTO> response = new ObjectListResponse<PlayerDTO>();
      response.setDtoList(new ArrayList<PlayerDTO>());
      players.forEach(item -> {
        response.getDtoList().add(PlayerDTOConverter.convertDomainToDTO(item));
      });
      response.setListSize(response.getDtoList().size());
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public ObjectDeletionResponse deletePlayer(ObjectDeletionRequest request) {
    Player player = playerRepository.findOne(request.getId());
    if (!(player == null)) {
      player.setRecordStatus(RecordStatus.INACTIVE);
      playerRepository.save(player);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public PlayerUpdateResponse updatePlayer(PlayerUpdateRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PlayerCreationResponse createPlayer(PlayerCreationRequest request) {
    Person person = null;
    if (request.getPersonId() > 0) {
      person = personRepository.findOne(request.getPersonId());
    }
    CategorySetup categorySetup = categorySetupRepository.findOne(request.getCategoryId());
    Player player = PlayerDTOConverter.convertRequestToDomain(request, categorySetup, person);
    Player persisted = playerRepository.save(player);
    PlayerCreationResponse response = new PlayerCreationResponse();
    response.setId(persisted.getId());
    response.setPlayerDTO(PlayerDTOConverter.convertDomainToDTO(persisted));
    response.setMessage("SUCESS");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }



  @Override
  public ObjectRetrievalResponse<PlayerDTO> getPlayerByIcPassport(ObjectRetrievalRequest request) {
    Player player = playerRepository.findByPerson_Nic(request.getUniqueId());
    if (!(player == null)) {
      ObjectRetrievalResponse<PlayerDTO> response = new ObjectRetrievalResponse<PlayerDTO>();
      response.setDto(PlayerDTOConverter.convertDomainToDTO(player));
      response.setId(player.getId());
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public ObjectRetrievalResponse<PlayerDTO> getPlayerByPlayerNo(ObjectRetrievalRequest request) {
    Player player = playerRepository.findByPlayerNumber(request.getUniqueId());
    if (!(player == null)) {
      ObjectRetrievalResponse<PlayerDTO> response = new ObjectRetrievalResponse<PlayerDTO>();
      response.setDto(PlayerDTOConverter.convertDomainToDTO(player));
      response.setId(player.getId());
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

}
