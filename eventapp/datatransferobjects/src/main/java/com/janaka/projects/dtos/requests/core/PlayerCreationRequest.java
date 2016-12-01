package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;

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

  @Override
  public String toString() {
    return "PlayerCreationRequest [eventId=" + eventId + ", playerNumber=" + playerNumber + ", playerName=" + playerName
        + ", icPassport=" + icPassport + ", address=" + address + ", contactNumber=" + contactNumber + ", weight="
        + weight + ", height=" + height + ", categoryId=" + categoryId + "]";
  }



}
