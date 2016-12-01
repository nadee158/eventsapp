package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.EventDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class EventCreationResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private EventDTO eventDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public EventDTO getEventDTO() {
    return eventDTO;
  }

  public void setEventDTO(EventDTO eventDTO) {
    this.eventDTO = eventDTO;
  }



}
