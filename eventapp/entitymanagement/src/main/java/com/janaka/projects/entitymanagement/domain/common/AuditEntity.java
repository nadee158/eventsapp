package com.janaka.projects.entitymanagement.domain.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class AuditEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Audited
  @Column(name = "uuid")
  private String uuId = null;

  @Audited()
  @Column(name = "creation_time", nullable = false)
  @CreatedDate
  @JsonFormat(pattern = ApplicationConstants.GLOBAL_DATE_TIME_FORMAT)
  private LocalDateTime creationTime;

  @Audited
  @LastModifiedDate
  @Column(name = "modification_time", nullable = true)
  @JsonFormat(pattern = ApplicationConstants.GLOBAL_DATE_TIME_FORMAT)
  private LocalDateTime modificationTime;


  @Audited
  @Column(name = "created_by_user", nullable = false)
  @CreatedBy
  private String createdByUser;

  @Audited
  @Column(name = "modified_by_user", nullable = true)
  @LastModifiedBy
  private String modifiedByUser;

  @Audited
  @Enumerated(EnumType.STRING)
  @Column(name = "record_status")
  private RecordStatus recordStatus;

  @Audited
  @Column(name = "operation")
  private String operation;

  @Audited
  @Column(name = "timestamp")
  private Long timestamp;

  @Version
  @Column(name = "version", nullable = true)
  private long version = 0;


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
      this.creationTime = LocalDateTime.now();
    }
    if (this.modificationTime == null) {
      this.modificationTime = LocalDateTime.now();
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

  public String getUuId() {
    return uuId;
  }

  public void setUuId(String uuId) {
    this.uuId = uuId;
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

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public LocalDateTime getModificationTime() {
    return modificationTime;
  }

  public void setModificationTime(LocalDateTime modificationTime) {
    this.modificationTime = modificationTime;
  }

  public RecordStatus getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(RecordStatus recordStatus) {
    this.recordStatus = recordStatus;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }



}
