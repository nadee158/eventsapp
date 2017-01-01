package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

public class PlayerDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String playerNumber = StringUtils.EMPTY;

  private long eventId;

  private String eventName;

  private long teamId;

  private String teamName;

  private double height;

  private double weight;

  private long categoryId;

  private String categorySetupName;

  private long personId = 0;

  private String fullName = StringUtils.EMPTY;

  private String nic = StringUtils.EMPTY;

  private String contactNumber = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String profileImagePath = StringUtils.EMPTY;

  private String address;

  private String recordStatus = StringUtils.EMPTY;

  private long version = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(String playerNumber) {
    this.playerNumber = playerNumber;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getProfileImagePath() {
    return profileImagePath;
  }

  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCategorySetupName() {
    return categorySetupName;
  }

  public void setCategorySetupName(String categorySetupName) {
    this.categorySetupName = categorySetupName;
  }


  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public long getEventId() {
    return eventId;
  }

  public void setEventId(long eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public long getTeamId() {
    return teamId;
  }

  public void setTeamId(long teamId) {
    this.teamId = teamId;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  @Override
  public String toString() {
    return "PlayerDTO [id=" + id + ", playerNumber=" + playerNumber + ", eventId=" + eventId + ", eventName="
        + eventName + ", teamId=" + teamId + ", teamName=" + teamName + ", height=" + height + ", weight=" + weight
        + ", categoryId=" + categoryId + ", categorySetupName=" + categorySetupName + ", personId=" + personId
        + ", fullName=" + fullName + ", nic=" + nic + ", contactNumber=" + contactNumber + ", email=" + email
        + ", profileImagePath=" + profileImagePath + ", address=" + address + ", recordStatus=" + recordStatus
        + ", version=" + version + "]";
  }



}
