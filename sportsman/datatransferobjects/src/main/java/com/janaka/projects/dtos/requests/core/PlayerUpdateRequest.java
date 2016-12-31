package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class PlayerUpdateRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  private long id;

  private String playerNumber = StringUtils.EMPTY;

  private long eventId;

  private String eventName;

  private String team;

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

  private MultipartFile file;

  private String savedImagePath;

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

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
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

  public String getCategorySetupName() {
    return categorySetupName;
  }

  public void setCategorySetupName(String categorySetupName) {
    this.categorySetupName = categorySetupName;
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
    return "PlayerUpdateRequest [id=" + id + ", playerNumber=" + playerNumber + ", eventId=" + eventId + ", eventName="
        + eventName + ", team=" + team + ", height=" + height + ", weight=" + weight + ", categoryId=" + categoryId
        + ", categorySetupName=" + categorySetupName + ", personId=" + personId + ", fullName=" + fullName + ", nic="
        + nic + ", contactNumber=" + contactNumber + ", email=" + email + ", profileImagePath=" + profileImagePath
        + ", address=" + address + ", recordStatus=" + recordStatus + ", version=" + version + "]";
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public String getSavedImagePath() {
    return savedImagePath;
  }

  public void setSavedImagePath(String savedImagePath) {
    this.savedImagePath = savedImagePath;
  }



}
