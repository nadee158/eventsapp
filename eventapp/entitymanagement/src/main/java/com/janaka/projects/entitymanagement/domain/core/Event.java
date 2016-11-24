package com.janaka.projects.entitymanagement.domain.core;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.entitymanagement.domain.common.AuditEntity;

@Audited
@Entity
@Table(name = "event",
    indexes = {@Index(name = "event_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "event_id_index", unique = true, columnList = "uuid"),
        @Index(name = "event_name_status_index", unique = true, columnList = "event_name,record_status")})
public class Event extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @NotNull
  @Column(name = "event_name")
  private String eventName = StringUtils.EMPTY;

  @Column(name = "event_date")
  @JsonFormat(pattern = ApplicationConstants.GLOBAL_DATE_TIME_FORMAT)
  private LocalDateTime eventDate;

  @Column(name = "event_venue")
  private String eventVenue;

  @Column(name = "days_of_game")
  private int daysOfGame;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public LocalDateTime getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDateTime eventDate) {
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

  @Override
  public String toString() {
    return "Event [id=" + id + ", eventName=" + eventName + ", eventDate=" + eventDate + ", eventVenue=" + eventVenue
        + ", daysOfGame=" + daysOfGame + "]";
  }



}
