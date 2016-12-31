package com.janaka.projects.services.web.rest.core;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.domain.core.TeamDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.TeamResponse;
import com.janaka.projects.services.core.TeamService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.TEAMS)
public class TeamController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private TeamService teamService;

  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomResponseEntity<ApiResponseObject<?>> createTeams(@Valid @RequestBody TeamDTO request) {
    TeamResponse response = teamService.createTeam(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<TeamResponse>(HttpStatus.OK, response),
        HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updateTeam(@Valid @RequestBody TeamDTO request) {
    TeamResponse response = teamService.updateTeam(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<TeamResponse>(HttpStatus.OK, response),
        HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> deleteTeam(@RequestBody ObjectDeletionRequest request) {
    ObjectDeletionResponse response = teamService.deleteTeam(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<TeamDTO> response = teamService.getAllActiveTeams();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<TeamDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<TeamDTO> response = teamService.getTeamById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<TeamDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<TeamDTO> getActiveSecurityUsers(@RequestBody TabularDataRequestModel request) {
    return teamService.getTeams(request);
  }



}
