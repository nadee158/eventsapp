package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.janaka.projects.common.util.CustomJsonDateDeserializer;
import com.janaka.projects.common.util.CustomJsonDateSerializer;
import com.janaka.projects.dtos.domain.usermanagement.MaritalStatusDTO;
import com.janaka.projects.dtos.domain.usermanagement.PrefixDTO;

public class SecurityUserProfileUpdateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotNull
  private long securityuserId;

  @NotNull
  private long personId;

  private PrefixDTO prefix = null;

  private int prefixCode;

  private String prefixName;

  private int maritalStatusCode;

  private String maritalStatusName;

  @NotNull
  private String fullName = StringUtils.EMPTY;


  @JsonDeserialize(using = CustomJsonDateDeserializer.class)
  @JsonSerialize(using = CustomJsonDateSerializer.class)
  private Date dateOfBirth = null;

  @NotNull
  @Size(min = 10, max = 12)
  private String nic = StringUtils.EMPTY;

  @NotNull
  private String mobileNumber = StringUtils.EMPTY;

  private String landNumber = StringUtils.EMPTY;

  @NotNull
  @Email()
  private String email = StringUtils.EMPTY;

  private String profileImagePath = StringUtils.EMPTY;

  private MaritalStatusDTO maritalStatus = null;

  @Column(name = "address")
  private String address;

  public long getSecurityuserId() {
    return securityuserId;
  }

  public void setSecurityuserId(long securityuserId) {
    this.securityuserId = securityuserId;
  }

  public long getPersonId() {
    return personId;
  }

  public void setPersonId(long personId) {
    this.personId = personId;
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

  public int getPrefixCode() {
    return prefixCode;
  }

  public void setPrefixCode(int prefixCode) {
    this.prefixCode = prefixCode;
  }

  public String getPrefixName() {
    return prefixName;
  }

  public void setPrefixName(String prefixName) {
    this.prefixName = prefixName;
  }

  public int getMaritalStatusCode() {
    return maritalStatusCode;
  }

  public void setMaritalStatusCode(int maritalStatusCode) {
    this.maritalStatusCode = maritalStatusCode;
  }

  public String getMaritalStatusName() {
    return maritalStatusName;
  }

  public void setMaritalStatusName(String maritalStatusName) {
    this.maritalStatusName = maritalStatusName;
  }

  @Override
  public String toString() {
    return "SecurityUserProfileUpdateRequest [securityuserId=" + securityuserId + ", personId=" + personId + ", prefix="
        + prefix + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth + ", nic=" + nic + ", mobileNumber="
        + mobileNumber + ", landNumber=" + landNumber + ", email=" + email + ", profileImagePath=" + profileImagePath
        + ", maritalStatus=" + maritalStatus + ", address=" + address + ",prefixCode=" + prefixCode + ",prefixName="
        + prefixName + ",maritalStatusCode=" + maritalStatusCode + ",maritalStatusName=" + maritalStatusName + "]";
  }

}
