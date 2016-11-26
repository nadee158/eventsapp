package com.janaka.projects.common.security;

import java.time.LocalDateTime;
import java.util.Collection;
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

  private LocalDateTime dateOfBirth = null;

  private String cotactNumber = StringUtils.EMPTY;

  private String email = StringUtils.EMPTY;

  private String userName = StringUtils.EMPTY;

  private String secret = StringUtils.EMPTY;

  private Set<Authority> myAuthorities = new HashSet<Authority>();

  private boolean customAccountExpired;

  private boolean customAccountLocked;

  private boolean customCredentialsExpired;

  private boolean customAccountEnabled;

  private List<String> activeSessions;

  private int customActiveSessionCount;



  public User(long id, UUID securityUserId, String userFullName, String profileImagePath, LocalDateTime dateOfBirth,
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
    this.myAuthorities = authorities;
    this.customAccountExpired = accountExpired;
    this.customAccountLocked = accountLocked;
    this.customCredentialsExpired = credentialsExpired;
    this.customAccountEnabled = accountEnabled;
  }


  public User() {
    super();
  }


  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.myAuthorities;
  }

  public long getId() {
    return id;
  }

  public String getSecret() {
    return secret;
  }


  @JsonIgnore
  public boolean isAccountExpired() {
    return customAccountExpired;
  }

  @JsonIgnore
  public boolean isAccountLocked() {
    return customAccountLocked;
  }

  @JsonIgnore
  public boolean isCredentialsExpired() {
    return customCredentialsExpired;
  }

  @JsonIgnore
  public boolean isAccountEnabled() {
    return customAccountEnabled;
  }


  @JsonIgnore
  @Override
  public String getPassword() {
    return this.secret;
  }

  @JsonIgnore
  @Override
  public String getUsername() {
    return this.userName;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return (!(this.customAccountExpired));
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return (!(this.customAccountLocked));
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return (!(this.customCredentialsExpired));
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return this.customAccountEnabled;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", securityUserId=" + securityUserId + ", userFullName=" + userFullName
        + ", profileImagePath=" + profileImagePath + ", dateOfBirth=" + dateOfBirth + ", cotactNumber=" + cotactNumber
        + ", email=" + email + ", userName=" + userName + ", secret=" + secret + ", myAuthorities=" + myAuthorities
        + ", customAccountExpired=" + customAccountExpired + ", customAccountLocked=" + customAccountLocked
        + ", customCredentialsExpired=" + customCredentialsExpired + ", customAccountEnabled=" + customAccountEnabled
        + ", activeSessions=" + activeSessions + ", customActiveSessionCount=" + customActiveSessionCount + "]";
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

  public List<String> getActiveSessions() {
    return activeSessions;
  }

  public void setActiveSessions(List<String> activeSessions) {
    this.activeSessions = activeSessions;
  }



  public UUID getSecurityUserId() {
    return securityUserId;
  }



  public void setSecurityUserId(UUID securityUserId) {
    this.securityUserId = securityUserId;
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



  public LocalDateTime getDateOfBirth() {
    return dateOfBirth;
  }



  public void setDateOfBirth(LocalDateTime dateOfBirth) {
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



  public Set<Authority> getMyAuthorities() {
    return myAuthorities;
  }



  public void setMyAuthorities(Set<Authority> myAuthorities) {
    this.myAuthorities = myAuthorities;
  }



  public boolean isCustomAccountExpired() {
    return customAccountExpired;
  }



  public void setCustomAccountExpired(boolean customAccountExpired) {
    this.customAccountExpired = customAccountExpired;
  }



  public boolean isCustomAccountLocked() {
    return customAccountLocked;
  }



  public void setCustomAccountLocked(boolean customAccountLocked) {
    this.customAccountLocked = customAccountLocked;
  }



  public boolean isCustomCredentialsExpired() {
    return customCredentialsExpired;
  }



  public void setCustomCredentialsExpired(boolean customCredentialsExpired) {
    this.customCredentialsExpired = customCredentialsExpired;
  }



  public boolean isCustomAccountEnabled() {
    return customAccountEnabled;
  }



  public void setCustomAccountEnabled(boolean customAccountEnabled) {
    this.customAccountEnabled = customAccountEnabled;
  }



  public int getCustomActiveSessionCount() {
    return customActiveSessionCount;
  }



  public void setCustomActiveSessionCount(int customActiveSessionCount) {
    this.customActiveSessionCount = customActiveSessionCount;
  }



  public void setId(long id) {
    this.id = id;
  }



}
