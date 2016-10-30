package com.janaka.projects.dtos.requests.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class ContactRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private long id = 0;

  private UUID contactId = null;

  private String firstName = StringUtils.EMPTY;

  private String lastName = StringUtils.EMPTY;

  private String selfDescription = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String mobile = StringUtils.EMPTY;

  private Date dateOfBirth = null;

  private String birthCity = StringUtils.EMPTY;

  private String gender = null;

  private float monthlyIncome;

  private int healthStatus;

  private String profileImagePath = StringUtils.EMPTY;

  private List<MultipartFile> files;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public UUID getContactId() {
    return contactId;
  }

  public void setContactId(UUID contactId) {
    this.contactId = contactId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSelfDescription() {
    return selfDescription;
  }

  public void setSelfDescription(String selfDescription) {
    this.selfDescription = selfDescription;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getBirthCity() {
    return birthCity;
  }

  public void setBirthCity(String birthCity) {
    this.birthCity = birthCity;
  }

  public float getMonthlyIncome() {
    return monthlyIncome;
  }

  public void setMonthlyIncome(float monthlyIncome) {
    this.monthlyIncome = monthlyIncome;
  }

  public int getHealthStatus() {
    return healthStatus;
  }

  public void setHealthStatus(int healthStatus) {
    this.healthStatus = healthStatus;
  }

  public String getProfileImagePath() {
    return profileImagePath;
  }

  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<MultipartFile> getFiles() {
    return files;
  }

  public void setFiles(List<MultipartFile> files) {
    this.files = files;
  }

  @Override
  public String toString() {
    return "ContactRequest [id=" + id + ", contactId=" + contactId + ", firstName=" + firstName + ", lastName="
        + lastName + ", selfDescription=" + selfDescription + ", email=" + email + ", mobile=" + mobile
        + ", dateOfBirth=" + dateOfBirth + ", birthCity=" + birthCity + ", gender=" + gender + ", monthlyIncome="
        + monthlyIncome + ", healthStatus=" + healthStatus + ", profileImagePath=" + profileImagePath + ", files="
        + files + "]";
  }

}
