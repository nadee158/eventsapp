package com.janaka.projects.entitymanagement.domain.common;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseDomain implements Serializable {

  private static final long serialVersionUID = 1L;

  @Audited
  @Column(name = "uuid")
  private String uuId = null;

  @Audited
  @Column(name = "creation_time", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date creationTime;

  @Audited
  @Column(name = "modification_time")
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modificationTime;

  @Audited
  @Column(name = "created_by_user", nullable = false)
  @CreatedBy
  private String createdByUser;

  @Audited
  @Column(name = "modified_by_user", nullable = false)
  @LastModifiedBy
  private String modifiedByUser;

  @Audited
  @Column(name = "is_deleted")
  private boolean isDeleted;

  @Audited
  @Column(name = "operation")
  private String operation;

  @Audited
  @Column(name = "timestamp")
  private Long timestamp;


  @PrePersist
  public void onPrePersist() {
    this.uuId = UUID.randomUUID().toString();
    if (StringUtils.isEmpty(this.createdByUser)) {
      this.createdByUser = "UNKNOWN";
    }
    if (StringUtils.isEmpty(this.modifiedByUser)) {
      this.modifiedByUser = "UNKNOWN";
    }
    if (this.creationTime == null) {
      this.creationTime = Calendar.getInstance().getTime();
    }
    if (this.modificationTime == null) {
      this.modificationTime = Calendar.getInstance().getTime();
    }
    audit("INSERT");
  }

  @PreUpdate
  public void onPreUpdate() {
    audit("UPDATE");
  }

  @PreRemove
  public void onPreRemove() {
    audit("DELETE");
  }

  private void audit(String operation) {
    setOperation(operation);
    setTimestamp((new Date()).getTime());
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

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }



}
