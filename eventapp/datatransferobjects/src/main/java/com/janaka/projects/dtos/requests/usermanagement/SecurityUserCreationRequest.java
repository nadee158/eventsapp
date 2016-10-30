package com.janaka.projects.dtos.requests.usermanagement;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

public class SecurityUserCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @NotNull
  @Size(min = 8, max = 30)
  private String userName = StringUtils.EMPTY;

  @NotNull
  @Size(min = 8, max = 100)
  private String secret = StringUtils.EMPTY;

  private long organizationId;

  private int prefixCode;

  private String fullName = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  @NotNull
  @Size(min = 10, max = 12)
  private String nic = StringUtils.EMPTY;

  private String mobileNumber = StringUtils.EMPTY;

  private String address;

  private List<Long> userRoles = null;

  private List<Long> explicitlyGrantedPermissions = null;

  private List<Long> explicitlyDeniedPermissions = null;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public long getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(long organizationId) {
    this.organizationId = organizationId;
  }

  public int getPrefixCode() {
    return prefixCode;
  }

  public void setPrefixCode(int prefixCode) {
    this.prefixCode = prefixCode;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Long> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(List<Long> userRoles) {
    this.userRoles = userRoles;
  }

  public List<Long> getExplicitlyGrantedPermissions() {
    return explicitlyGrantedPermissions;
  }

  public void setExplicitlyGrantedPermissions(List<Long> explicitlyGrantedPermissions) {
    this.explicitlyGrantedPermissions = explicitlyGrantedPermissions;
  }

  public List<Long> getExplicitlyDeniedPermissions() {
    return explicitlyDeniedPermissions;
  }

  public void setExplicitlyDeniedPermissions(List<Long> explicitlyDeniedPermissions) {
    this.explicitlyDeniedPermissions = explicitlyDeniedPermissions;
  }



}
