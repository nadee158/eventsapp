package com.janaka.projects.common.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  private long id;

  private UUID securityUserId = null;

  private String userFullName = StringUtils.EMPTY;

  private String profileImagePath = StringUtils.EMPTY;

  private Date dateOfBirth = null;

  private String cotactNumber = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String userName = StringUtils.EMPTY;

  private String secret = StringUtils.EMPTY;

  private Set<Authority> authorities = new HashSet<Authority>();

  private boolean accountExpired;

  private boolean accountLocked;

  private boolean credentialsExpired;

  private boolean accountEnabled;

  private List<String> activeSessions;

  private int activeSessionCount;



  public User(long id, UUID securityUserId, String userFullName, String profileImagePath, Date dateOfBirth,
      String cotactNumber, String email, String userName, String secret, Set<Authority> authorities,
      boolean accountExpired, boolean accountLocked, boolean credentialsExpired, boolean accountEnabled) {
    this();
    this.id = id;
    this.securityUserId = securityUserId;
    this.userFullName = userFullName;
    this.profileImagePath = profileImagePath;
    this.dateOfBirth = dateOfBirth;
    this.cotactNumber = cotactNumber;
    this.email = email;
    this.userName = userName;
    this.secret = secret;
    this.authorities = authorities;
    this.accountExpired = accountExpired;
    this.accountLocked = accountLocked;
    this.credentialsExpired = credentialsExpired;
    this.accountEnabled = accountEnabled;
  }



  private User() {
    super();
  }



  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @JsonIgnore
  public long getId() {
    return id;
  }

  public String getSecret() {
    return secret;
  }


  @JsonIgnore
  public boolean isAccountExpired() {
    return accountExpired;
  }

  @JsonIgnore
  public boolean isAccountLocked() {
    return accountLocked;
  }

  @JsonIgnore
  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  @JsonIgnore
  public boolean isAccountEnabled() {
    return accountEnabled;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }

  @Override
  public String getPassword() {
    return this.secret;
  }

  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return (!(this.accountExpired));
  }

  @Override
  public boolean isAccountNonLocked() {
    return (!(this.accountLocked));
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return (!(this.credentialsExpired));
  }

  @Override
  public boolean isEnabled() {
    return this.accountEnabled;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + ": " + getUsername();
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  @JsonIgnore
  public List<String> getActiveSessions() {
    return activeSessions;
  }

  public void setActiveSessions(List<String> activeSessions) {
    this.activeSessions = activeSessions;
  }

  @JsonIgnore
  public int getActiveSessionCount() {
    return activeSessionCount;
  }

  public void setActiveSessionCount(int activeSessionCount) {
    this.activeSessionCount = activeSessionCount;
  }

  public String getUserFullName() {
    return userFullName;
  }

  public void setUserFullName(String userFullName) {
    this.userFullName = userFullName;
  }

  public String getProfileImagePath() {
    return profileImagePath;
  }

  public void setProfileImagePath(String profileImagePath) {
    this.profileImagePath = profileImagePath;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getCotactNumber() {
    return cotactNumber;
  }

  public void setCotactNumber(String cotactNumber) {
    this.cotactNumber = cotactNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UUID getSecurityUserId() {
    return securityUserId;
  }


}
