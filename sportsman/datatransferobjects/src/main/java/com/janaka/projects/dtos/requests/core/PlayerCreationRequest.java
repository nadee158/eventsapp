package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PlayerCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long eventId;

  private String playerNumber;

  private String playerName;

  private String icPassport;

  private String address;

  private String contactNumber;

  private double weight;

  private double height;

  private long categoryId;

  private long teamId;

  private String teamName;

  private MultipartFile file;

  private String savedImagePath;

  private String dateOfBirth;

  private String email;

  private long personId;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public long getEventId() {
    return eventId;
  }

  public void setEventId(long eventId) {
    this.eventId = eventId;
  }

  public String getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(String playerNumber) {
    this.playerNumber = playerNumber;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getIcPassport() {
    return icPassport;
  }

  public void setIcPassport(String icPassport) {
    this.icPassport = icPassport;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(long categoryId) {
    this.categoryId = categoryId;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
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

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getSavedImagePath() {
    return savedImagePath;
  }

  public void setSavedImagePath(String savedImagePath) {
    this.savedImagePath = savedImagePath;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
  }

  @Override
  public String toString() {
    return "PlayerCreationRequest [eventId=" + eventId + ", playerNumber=" + playerNumber + ", playerName=" + playerName
        + ", icPassport=" + icPassport + ", address=" + address + ", contactNumber=" + contactNumber + ", weight="
        + weight + ", height=" + height + ", categoryId=" + categoryId + ", teamId=" + teamId + ", teamName=" + teamName
        + ", file=" + file + ", savedImagePath=" + savedImagePath + ", dateOfBirth=" + dateOfBirth + ", email=" + email
        + ", personId=" + personId + "]";
  }



}
