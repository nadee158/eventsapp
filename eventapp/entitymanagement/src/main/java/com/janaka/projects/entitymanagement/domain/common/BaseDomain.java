package com.janaka.projects.entitymanagement.domain.common;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.janaka.projects.common.security.User;

@MappedSuperclass
public class BaseDomain implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "uuid")
  private String uuId = null;

  @Column(name = "creation_time", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationTime;

  @Column(name = "modification_time")
  @Temporal(TemporalType.TIMESTAMP)
  private Date modificationTime;

  @Column(name = "created_by_user", nullable = false)
  @CreatedBy
  private String createdByUser;

  @Column(name = "modified_by_user", nullable = false)
  @LastModifiedBy
  private String modifiedByUser;

  @Column(name = "is_deleted")
  private boolean isDeleted;



  @PrePersist
  public void prePersist() {
    this.uuId = UUID.randomUUID().toString();
    Date now = Calendar.getInstance().getTime();
    this.creationTime = now;
    this.modificationTime = now;
    if (StringUtils.isEmpty(this.createdByUser)) {
      this.createdByUser = getUsernameOfAuthenticatedUser();
    }
    if (StringUtils.isEmpty(this.modifiedByUser)) {
      this.modifiedByUser = createdByUser;
    }
  }

  @PreUpdate
  public void preUpdate() {
    this.modificationTime = Calendar.getInstance().getTime();
    if (StringUtils.isEmpty(this.modifiedByUser)) {
      this.modifiedByUser = getUsernameOfAuthenticatedUser();;
    }
  }


  private String getUsernameOfAuthenticatedUser() {
    String userName = "UNKNOWN";
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null) {
      userName = "ANNONYMOUS_USER";
    } else {
      if (!authentication.isAuthenticated()) {
        userName = "UNAUTHORIZED_USER";
      }

      if (!(authentication.getPrincipal() == null)) {
        System.out.println("authentication.getPrincipal() " + authentication.getPrincipal());
        if (authentication.getPrincipal() instanceof User) {
          userName = ((User) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
          userName = (String) authentication.getPrincipal();
        }

      }
    }

    return userName;
  }


  public String getCreatedByUser() {
    return createdByUser;
  }

  public void setCreatedByUser(String createdByUser) {
    this.createdByUser = createdByUser;
  }

  public String getModifiedByUser() {
    return modifiedByUser;
  }

  public void setModifiedByUser(String modifiedByUser) {
    this.modifiedByUser = modifiedByUser;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(Date creationTime) {
    this.creationTime = creationTime;
  }

  public Date getModificationTime() {
    return modificationTime;
  }

  public void setModificationTime(Date modificationTime) {
    this.modificationTime = modificationTime;
  }


  public String getUuId() {
    return uuId;
  }

  public void setUuId(String uuId) {
    this.uuId = uuId;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean isDeleted) {
    this.isDeleted = isDeleted;
  }

  @Override
  public String toString() {
    return "BaseDomain [uuId=" + uuId + ", creationTime=" + creationTime + ", modificationTime=" + modificationTime
        + ", createdByUser=" + createdByUser + ", modifiedByUser=" + modifiedByUser + ", isDeleted=" + isDeleted + "]";
  }



}
