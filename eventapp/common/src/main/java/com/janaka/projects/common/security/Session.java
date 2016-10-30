package com.janaka.projects.common.security;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

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

  private Collection<? extends GrantedAuthority> authorities = new HashSet<Authority>();

  private String name;


  public Session(User user) {
    this.id = UUID.randomUUID();
    this.ownerId = user.getSecurityUserId();
    this.startTimestamp = Calendar.getInstance().getTime();
    this.lastRequestTimestamp = Calendar.getInstance().getTime();
    this.expires = EXPIRY_PERIOD;
    this.authorities = user.getAuthorities();
    this.name = user.getUserName();
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

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public Object getCredentials() {
    return this.getToken();
  }

  @Override
  public Object getDetails() {
    return this.id.toString();
  }

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

  public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    this.authorities = authorities;
  }



}
