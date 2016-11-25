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

import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;
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



}
