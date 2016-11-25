package com.janaka.projects.services.core;

import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;

public interface EventService {

  public EventCreationResponse createEvent(EventCreationRequest request);
}
