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
import com.janaka.projects.dtos.domain.core.EventDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.requests.core.EventUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;
import com.janaka.projects.dtos.responses.core.EventUpdateResponse;
import com.janaka.projects.services.core.EventService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.EVENTS)
public class EventController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private EventService eventService;

  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomResponseEntity<ApiResponseObject<?>> createEvents(@Valid @RequestBody EventCreationRequest request) {
    EventCreationResponse response = eventService.createEvent(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<EventCreationResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updateEvent(@Valid @RequestBody EventUpdateRequest request) {
    EventUpdateResponse response = eventService.updateEvent(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<EventUpdateResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.DELETE)
  public CustomResponseEntity<ApiResponseObject<?>> deleteEvent(@RequestBody ObjectDeletionRequest request) {
    ObjectDeletionResponse response = eventService.deleteEvent(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<EventDTO> response = eventService.getAllActiveEvents();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<EventDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<EventDTO> response = eventService.getEventById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<EventDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<EventDTO> getActiveSecurityUsers(@RequestBody TabularDataRequestModel request) {
    return eventService.getEvents(request);
  }



}
