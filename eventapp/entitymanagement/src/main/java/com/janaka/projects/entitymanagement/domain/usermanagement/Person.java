package com.janaka.projects.entitymanagement.domain.usermanagement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;

import com.janaka.projects.entitymanagement.domain.common.BaseDomain;
import com.janaka.projects.entitymanagement.enums.MaritalStatus;
import com.janaka.projects.entitymanagement.enums.Prefix;


@Entity
@Table(name = "person",
    indexes = {@Index(name = "person_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "person_person_id_index", unique = true, columnList = "uuid"),
        @Index(name = "person_nic_index", unique = true, columnList = "nic")})
public class Person extends BaseDomain {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @Column(name = "prefix")
  @Enumerated(EnumType.STRING)
  private Prefix prefix;

  @Column(name = "full_name")
  private String fullName = StringUtils.EMPTY;

  @Column(name = "date_of_birth")
  private Date dateOfBirth = null;

  @NotNull
  @Size(min = 10, max = 12)
  @Column(name = "nic")
  private String nic = StringUtils.EMPTY;

  @Column(name = "mobile_number")
  private String mobileNumber = StringUtils.EMPTY;

  @Column(name = "land_number")
  private String landNumber = StringUtils.EMPTY;

  @Column(name = "email")
  @Email()
  private String email = StringUtils.EMPTY;

  @Column(name = "profile_image_path")
  private String profileImagePath = StringUtils.EMPTY;

  @Enumerated(EnumType.STRING)
  @Column(name = "marital_status")
  private MaritalStatus maritalStatus = null;

  @Column(name = "address")
  private String address;


  @Version
  @Column(name = "version_number")
  private long versionNumber = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Prefix getPrefix() {
    return prefix;
  }

  public void setPrefix(Prefix prefix) {
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

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(MaritalStatus maritalStatus) {
    this.maritalStatus = maritalStatus;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(long versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public String toString() {
    return "Person [id=" + id + ", prefix=" + prefix + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
        + ", nic=" + nic + ", mobileNumber=" + mobileNumber + ", landNumber=" + landNumber + ", email=" + email
        + ", profileImagePath=" + profileImagePath + ", maritalStatus=" + maritalStatus + ", address=" + address
        + ", versionNumber=" + versionNumber + "]";
  }



}
