package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.TeamDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.TeamResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.TeamRepository;
import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.entitymanagement.specifications.core.TeamSpecifications;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.TeamDTOConverter;
import com.janaka.projects.services.core.TeamService;

@Service("teamService")
public class TeamServiceImpl extends BusinessService implements TeamService {

  @Autowired
  private TeamRepository teamRepository;

  @Override
  public TeamResponse createTeam(TeamDTO request) {
    Team team = TeamDTOConverter.convertRequestToDomain(request);
    Team persisted = teamRepository.save(team);
    TeamResponse response = new TeamResponse();
    response.setTeamDTO(TeamDTOConverter.convertDomainToDTO(persisted));
    response.setId(persisted.getId());
    response.setMessage("SAVED_SUCCESSFULLY");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }

  @Override
  public TeamResponse updateTeam(TeamDTO request) {
    Team team = teamRepository.findOne(request.getId());
    if (!(team == null)) {
      team = TeamDTOConverter.updateDomainFromRequest(request, team);
      Team updatedTeam = teamRepository.save(team);
      TeamResponse response = new TeamResponse();
      response.setTeamDTO(TeamDTOConverter.convertDomainToDTO(updatedTeam));
      response.setId(updatedTeam.getId());
      response.setMessage("UPDATED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Team was not found!");
    }
  }

  @Override
  public ObjectDeletionResponse deleteTeam(ObjectDeletionRequest request) {
    Team teamFromDb = teamRepository.findOne(request.getId());
    if (!(teamFromDb == null)) {
      teamFromDb.setRecordStatus(RecordStatus.INACTIVE);
      Team updatedTeam = teamRepository.save(teamFromDb);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setId(updatedTeam.getId());
      response.setMessage("DELETED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Age Group was not found!");
    }
  }

  @Override
  public ObjectListResponse<TeamDTO> getAllActiveTeams() {
    List<Team> activeTeams = teamRepository.findByRecordStatus(RecordStatus.ACTIVE);
    System.out.println("activeTeams :" + activeTeams);
    if (!(activeTeams == null || activeTeams.isEmpty())) {
      ObjectListResponse<TeamDTO> response = new ObjectListResponse<TeamDTO>();
      List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
      activeTeams.forEach(event -> {
        dtoList.add(TeamDTOConverter.convertDomainToDTO(event));
      });
      response.setDtoList(dtoList);
      response.setListSize(dtoList.size());
      response.setMessage("LISTED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public ObjectRetrievalResponse<TeamDTO> getTeamById(ObjectRetrievalRequest request) {
    Team teamFromDb = teamRepository.findOne(request.getId());
    if (!(teamFromDb == null)) {
      ObjectRetrievalResponse<TeamDTO> response = new ObjectRetrievalResponse<TeamDTO>();
      response.setDto(TeamDTOConverter.convertDomainToDTO(teamFromDb));
      response.setId(teamFromDb.getId());
      response.setMessage("RETRIEVED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Team was not found!");
    }
  }

  @Override
  public TabularDataResponseModel<TeamDTO> getTeams(TabularDataRequestModel request) {
    DataTablesOutput<Team> domainResponse = teamRepository.findAll(request);
    TabularDataResponseModel<TeamDTO> response = new TabularDataResponseModel<TeamDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<TeamDTO> dtoList = new ArrayList<TeamDTO>();
        domainResponse.getData().forEach(event -> {
          dtoList.add(TeamDTOConverter.convertDomainToDTO(event));
        });
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
  public long getActiveCount() {
    return teamRepository.count(TeamSpecifications.isNotDeleted());
  }


}
