package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.EventDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.requests.core.EventUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;
import com.janaka.projects.dtos.responses.core.EventUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.EventRepository;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.entitymanagement.specifications.core.EventSpecifications;
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

  @Override
  public EventUpdateResponse updateEvent(EventUpdateRequest request) {
    Event eventFromDb = eventRepository.findOne(request.getId());
    if (!(eventFromDb == null)) {
      eventFromDb = EventDTOConverter.updateDomainFromRequest(request, eventFromDb);
      Event updatedEvent = eventRepository.save(eventFromDb);
      EventUpdateResponse response = new EventUpdateResponse();
      response.setEventDTO(EventDTOConverter.convertDomainToDTO(updatedEvent));
      response.setId(updatedEvent.getId());
      response.setMessage("UPDATED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public ObjectDeletionResponse deleteEvent(ObjectDeletionRequest request) {
    Event eventFromDb = eventRepository.findOne(request.getId());
    if (!(eventFromDb == null)) {
      eventFromDb.setRecordStatus(RecordStatus.INACTIVE);
      Event updatedEvent = eventRepository.save(eventFromDb);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setId(updatedEvent.getId());
      response.setMessage("DELETED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public ObjectListResponse<EventDTO> getAllActiveEvents() {
    List<Event> activeEvents = eventRepository.findByRecordStatus(RecordStatus.ACTIVE);
    System.out.println("activeEvents :" + activeEvents);
    if (!(activeEvents == null || activeEvents.isEmpty())) {
      ObjectListResponse<EventDTO> response = new ObjectListResponse<EventDTO>();
      List<EventDTO> dtoList = new ArrayList<EventDTO>();
      activeEvents.forEach(event -> {
        dtoList.add(EventDTOConverter.convertDomainToDTO(event));
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
  public ObjectRetrievalResponse<EventDTO> getEventById(ObjectRetrievalRequest request) {
    Event eventFromDb = eventRepository.findOne(request.getId());
    if (!(eventFromDb == null)) {
      ObjectRetrievalResponse<EventDTO> response = new ObjectRetrievalResponse<EventDTO>();
      response.setDto(EventDTOConverter.convertDomainToDTO(eventFromDb));
      response.setId(eventFromDb.getId());
      response.setMessage("RETRIEVED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public TabularDataResponseModel<EventDTO> getEvents(TabularDataRequestModel request) {
    DataTablesOutput<Event> domainResponse = eventRepository.findAll(request, EventSpecifications.isNotDeleted());
    TabularDataResponseModel<EventDTO> response = new TabularDataResponseModel<EventDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<EventDTO> dtoList = new ArrayList<>();
        domainResponse.getData().forEach(event -> {
          dtoList.add(EventDTOConverter.convertDomainToDTO(event));
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

}
