package com.janaka.projects.dtos.domain.usermanagement;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.janaka.projects.common.util.CustomJsonDateDeserializer;
import com.janaka.projects.common.util.CustomJsonDateSerializer;



public class PersonDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id = 0;

  private PrefixDTO prefix;

  private String fullName = StringUtils.EMPTY;

  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  @JsonSerialize(using = CustomJsonDateSerializer.class)
  private Date dateOfBirth = null;

  private String nic = StringUtils.EMPTY;

  private String mobileNumber = StringUtils.EMPTY;

  private String landNumber = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String profileImagePath = StringUtils.EMPTY;

  private MaritalStatusDTO maritalStatus;

  private String address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public PrefixDTO getPrefix() {
    return prefix;
  }

  public void setPrefix(PrefixDTO prefix) {
    this.prefix = prefix;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getLandNumber() {
    return landNumber;
  }

  public void setLandNumber(String landNumber) {
    this.landNumber = landNumber;
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

  public MaritalStatusDTO getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatusDTO maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }



}
