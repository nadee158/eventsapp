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
import com.janaka.projects.dtos.domain.core.GradeBeltDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.GradeBeltResponse;
import com.janaka.projects.services.core.GradeBeltService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.GRADE_BELTS)
public class GradeBeltController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private GradeBeltService gradeBeltService;

  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomResponseEntity<ApiResponseObject<?>> createGradeBelts(@Valid @RequestBody GradeBeltDTO request) {
    GradeBeltResponse response = gradeBeltService.createGradeBelt(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<GradeBeltResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updateGradeBelt(@Valid @RequestBody GradeBeltDTO request) {
    GradeBeltResponse response = gradeBeltService.updateGradeBelt(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<GradeBeltResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> deleteGradeBelt(@RequestBody ObjectDeletionRequest request) {
    ObjectDeletionResponse response = gradeBeltService.deleteGradeBelt(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<GradeBeltDTO> response = gradeBeltService.getAllActiveGradeBelts();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<GradeBeltDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<GradeBeltDTO> response = gradeBeltService.getGradeBeltById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<GradeBeltDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<GradeBeltDTO> getActiveSecurityUsers(@RequestBody TabularDataRequestModel request) {
    return gradeBeltService.getGradeBelts(request);
  }

  @RequestMapping(value = ServiceEndpoints.COUNT, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCount() {
    long count = gradeBeltService.getActiveCount();
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<Long>(HttpStatus.OK, count),
        HttpStatus.OK);
  }



}
