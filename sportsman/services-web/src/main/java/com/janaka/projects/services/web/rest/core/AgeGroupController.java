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
import com.janaka.projects.dtos.domain.core.AgeGroupDTO;
import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupCreationRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupCreationResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupUpdateResponse;
import com.janaka.projects.services.core.AgeGroupService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.AGE_GROUPS)
public class AgeGroupController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private AgeGroupService ageGroupService;

  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomResponseEntity<ApiResponseObject<?>> createAgeGroups(
      @Valid @RequestBody AgeGroupCreationRequest request) {
    AgeGroupCreationResponse response = ageGroupService.createAgeGroup(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<AgeGroupCreationResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updateAgeGroup(@Valid @RequestBody AgeGroupUpdateRequest request) {
    AgeGroupUpdateResponse response = ageGroupService.updateAgeGroup(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<AgeGroupUpdateResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> deleteAgeGroup(@RequestBody ObjectDeletionRequest request) {
    ObjectDeletionResponse response = ageGroupService.deleteAgeGroup(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<AgeGroupDTO> response = ageGroupService.getAllActiveAgeGroups();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<AgeGroupDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<AgeGroupDTO> response = ageGroupService.getAgeGroupById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<AgeGroupDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<AgeGroupDTO> getActiveSecurityUsers(@RequestBody TabularDataRequestModel request) {
    return ageGroupService.getAgeGroups(request);
  }



}
