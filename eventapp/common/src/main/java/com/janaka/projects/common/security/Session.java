package com.janaka.projects.common.security;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Session implements Authentication {

  // 30 mins
  public static final long EXPIRY_PERIOD = ((1000 * 60) * 30);

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private UUID id = null;

  private String token = StringUtils.EMPTY;

  private UUID ownerId = null;

  private Date startTimestamp = null;

  private Date lastRequestTimestamp = null;

  private boolean isAuthenticated = false;

  private long expires;

  private List<Authority> myAuthorities = new ArrayList<Authority>();

  private String userName;


  public Session() {
    super();
  }

  public Session(User user) {
    this.id = UUID.randomUUID();
    this.ownerId = user.getSecurityUserId();
    this.startTimestamp = Calendar.getInstance().getTime();
    this.lastRequestTimestamp = Calendar.getInstance().getTime();
    this.expires = EXPIRY_PERIOD;
    this.myAuthorities = constructAuthorities(user.getAuthorities());
    this.userName = user.getUserName();
  }

  private List<Authority> constructAuthorities(Collection<? extends GrantedAuthority> authorities2) {
    if (!(authorities2 == null || authorities2.isEmpty())) {
      List<Authority> authorities = new ArrayList<Authority>();
      authorities2.forEach(auth -> {
        authorities.add(new Authority(auth.toString()));
      });
    }
    return null;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public UUID getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(UUID ownerId) {
    this.ownerId = ownerId;
  }

  public Date getStartTimestamp() {
    return startTimestamp;
  }

  public void setStartTimestamp(Date startTimestamp) {
    this.startTimestamp = startTimestamp;
  }

  public Date getLastRequestTimestamp() {
    return lastRequestTimestamp;
  }

  public void setLastRequestTimestamp(Date lastRequestTimestamp) {
    this.lastRequestTimestamp = lastRequestTimestamp;
  }

  @JsonIgnore
  @Override
  public String getName() {
    return this.userName;
  }



  @JsonIgnore
  @Override
  public Object getCredentials() {
    return this.getToken();
  }

  @JsonIgnore
  @Override
  public Object getDetails() {
    return this.id.toString();
  }

  @JsonIgnore
  @Override
  public Object getPrincipal() {
    return this.ownerId;
  }

  @Override
  public boolean isAuthenticated() {
    return this.isAuthenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.isAuthenticated = isAuthenticated;
  }

  public long getExpires() {
    return expires;
  }

  public void setExpires(long expires) {
    this.expires = expires;
  }

  @Override
  public String toString() {
    return "Session [id=" + id + ", token=" + token + ", ownerId=" + ownerId + ", startTimestamp=" + startTimestamp
        + ", lastRequestTimestamp=" + lastRequestTimestamp + ", isAuthenticated=" + isAuthenticated + ", expires="
        + expires + "]";
  }



  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return myAuthorities;
  }

  public List<Authority> getMyAuthorities() {
    return myAuthorities;
  }

  public void setMyAuthorities(List<Authority> myAuthorities) {
    this.myAuthorities = myAuthorities;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }



}
