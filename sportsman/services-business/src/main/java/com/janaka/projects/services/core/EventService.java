package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
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

public interface EventService {

  public EventCreationResponse createEvent(EventCreationRequest request);

  public EventUpdateResponse updateEvent(EventUpdateRequest request);

  public ObjectDeletionResponse deleteEvent(ObjectDeletionRequest request);

  public ObjectListResponse<EventDTO> getAllActiveEvents();

  public ObjectRetrievalResponse<EventDTO> getEventById(ObjectRetrievalRequest request);

  public TabularDataResponseModel<EventDTO> getEvents(TabularDataRequestModel request);

  public long getActiveCount();
}
