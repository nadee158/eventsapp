package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class EventCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String eventName = StringUtils.EMPTY;

  private String eventDate;

  private String eventVenue;

  private int daysOfGame;

  private String recordStatus;

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }


  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public String getEventVenue() {
    return eventVenue;
  }

  public void setEventVenue(String eventVenue) {
    this.eventVenue = eventVenue;
  }

  public int getDaysOfGame() {
    return daysOfGame;
  }

  public void setDaysOfGame(int daysOfGame) {
    this.daysOfGame = daysOfGame;
  }

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }

  @Override
  public String toString() {
    return "EventCreationRequest [eventName=" + eventName + ", eventDate=" + eventDate + ", eventVenue=" + eventVenue
        + ", daysOfGame=" + daysOfGame + ", recordStatus=" + recordStatus + "]";
  }


}
