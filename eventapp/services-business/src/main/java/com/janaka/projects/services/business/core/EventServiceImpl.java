package com.janaka.projects.services.business.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.EventRepository;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.EventDTOConverter;
import com.janaka.projects.services.core.EventService;

@Service("eventService")
public class EventServiceImpl extends BusinessService implements EventService {

  @Autowired
  private EventRepository eventRepository;

  @Override
  public EventCreationResponse createEvent(EventCreationRequest request) {
    Event event = EventDTOConverter.convertRequestToDomain(request);
    Event persisted = eventRepository.save(event);
    EventCreationResponse response = new EventCreationResponse();
    response.setEventDTO(EventDTOConverter.convertDomainToDTO(persisted));
    response.setId(persisted.getId());
    response.setMessage("SAVED_SUCCESSFULLY");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }

}
