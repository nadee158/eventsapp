package com.janaka.projects.dtos.requests.core.mock;

import java.time.LocalDateTime;

import com.janaka.projects.dtos.requests.core.EventCreationRequest;

public class MockEventUtil {

  public static EventCreationRequest getEventCreationRequest() {
    EventCreationRequest request = new EventCreationRequest();
    request.setDaysOfGame(10);
    request.setEventDate(LocalDateTime.now());
    request.setEventName("Test Event");
    request.setEventVenue("Test Event Venue");
    request.setRecordStatus("A");
    return request;
  }

}
