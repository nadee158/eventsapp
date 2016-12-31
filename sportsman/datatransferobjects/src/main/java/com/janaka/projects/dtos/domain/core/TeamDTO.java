package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;

public class TeamDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String teamName;

  private String recordStatus;

  private long version;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "TeamDTO [id=" + id + ", teamName=" + teamName + ", recordStatus=" + recordStatus + ", version=" + version
        + "]";
  }



}
