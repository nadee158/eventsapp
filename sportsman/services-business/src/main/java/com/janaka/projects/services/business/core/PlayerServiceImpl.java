package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
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
import com.janaka.projects.entitymanagement.dataaccessobjects.core.TeamRepository;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.entitymanagement.specifications.core.PlayerSpecifications;
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

  @Autowired
  private TeamRepository teamRepository;


  @Override
  public TabularDataResponseModel<PlayerDTO> getPlayers(TabularDataRequestModel request) {
    Specification<Player> customSpecification = getSecrhSpecification(request.getCustomData());
    DataTablesOutput<Player> domainResponse = playerRepository.findAll(request, customSpecification);
    TabularDataResponseModel<PlayerDTO> response = new TabularDataResponseModel<PlayerDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<PlayerDTO> dtoList = new ArrayList<PlayerDTO>();
        for (Player domain : domainResponse.getData()) {
          dtoList.add(PlayerDTOConverter.convertDomainToDTO(domain));
        }
        response.setData(dtoList);
      }
      response.setDraw(domainResponse.getDraw());
      response.setError(domainResponse.getError());
      response.setRecordsFiltered(domainResponse.getRecordsFiltered());
      response.setRecordsTotal(domainResponse.getRecordsTotal());
    }
    return response;
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
    Player playerFromDB = playerRepository.findOne(request.getId());
    PlayerUpdateResponse response = new PlayerUpdateResponse();
    if (!(playerFromDB == null)) {
      Person person = null;
      if (StringUtils.isNotEmpty(request.getNic())) {
        person = personRepository.findByNic(request.getNic());
      }
      Team team = teamRepository.findOne(request.getTeamId());
      CategorySetup categorySetup = categorySetupRepository.findOne(request.getCategoryId());
      Player player = PlayerDTOConverter.updateDomainFromRequest(request, categorySetup, playerFromDB, person, team);
      Player persisted = playerRepository.save(player);
      response.setId(persisted.getId());
      response.setPlayerDTO(PlayerDTOConverter.convertDomainToDTO(persisted));
      response.setMessage("SUCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    throw new ResultsNotFoundException("Updated player was not found!");
  }


  @Override
  public PlayerCreationResponse createPlayer(PlayerCreationRequest request) {
    Person person = null;
    if (StringUtils.isNotEmpty(request.getIcPassport())) {
      person = personRepository.findByNic(request.getIcPassport());
    }
    CategorySetup categorySetup = categorySetupRepository.findOne(request.getCategoryId());
    Team team = teamRepository.findOne(request.getTeamId());
    Player player = PlayerDTOConverter.convertRequestToDomain(request, categorySetup, person, team);
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

  private Specification<Player> getSecrhSpecification(Map<String, Object> customData) {

    if (!(customData == null || customData.isEmpty())) {

      String playerName = null;
      String playerNumber = null;
      String nic = null;
      String contactNumber = null;


      if (customData.containsKey("playerName")) {
        playerName = (String) customData.get("playerName");
      }

      if (customData.containsKey("playerNumber")) {
        playerNumber = (String) customData.get("playerNumber");
      }

      if (customData.containsKey("nic")) {
        nic = (String) customData.get("nic");
      }

      if (customData.containsKey("contactNumber")) {
        contactNumber = (String) customData.get("contactNumber");
      }

      return PlayerSpecifications.combinedSpecifications(playerName, playerNumber, nic, contactNumber);


    }
    return null;
  }

  @Override
  public long getActiveCount() {
    return playerRepository.count(PlayerSpecifications.isNotDeleted());
  }



}
