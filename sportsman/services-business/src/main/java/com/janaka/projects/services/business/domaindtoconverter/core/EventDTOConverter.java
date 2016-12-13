package com.janaka.projects.services.business.domaindtoconverter.core;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.dtos.domain.core.EventDTO;
import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.requests.core.EventUpdateRequest;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class EventDTOConverter {

  public static Event convertRequestToDomain(EventCreationRequest request) {
    if (!(request == null)) {
      Event event = new Event();
      event.setDaysOfGame(request.getDaysOfGame());
      event.setEventDate(CommonUtil.getParsedDate(request.getEventDate()));
      event.setEventName(request.getEventName());
      event.setEventVenue(request.getEventVenue());
      if (StringUtils.isNoneEmpty(request.getRecordStatus())) {
        event.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      }
      return event;
    }
    return null;
  }

  public static EventDTO convertDomainToDTO(Event event) {
    if (!(event == null)) {
      EventDTO dto = new EventDTO();
      dto.setDaysOfGame(event.getDaysOfGame());
      dto.setEventDate(CommonUtil.getFormatteDate(event.getEventDate()));
      dto.setEventName(event.getEventName());
      dto.setEventVenue(event.getEventVenue());
      dto.setId(event.getId());
      dto.setRecordStatus(event.getRecordStatus().toString());
      return dto;
    }
    return null;
  }

  public static Event updateDomainFromRequest(EventUpdateRequest request, Event eventFromDb) {
    if (!(eventFromDb == null || request == null)) {
      eventFromDb.setDaysOfGame(request.getDaysOfGame());
      eventFromDb.setEventDate(CommonUtil.getParsedDate(request.getEventDate()));
      eventFromDb.setEventName(request.getEventName());
      eventFromDb.setEventVenue(request.getEventVenue());
      if (StringUtils.isNoneEmpty(request.getRecordStatus())) {
        eventFromDb.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      }
    }
    return eventFromDb;
  }

}
