package com.janaka.projects.entitymanagement.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

@Entity
@Table(name = "feedback"/* , schema = ApplicationConstants.SCHEMA_CORE */,
    indexes = {@Index(name = "feedback_id_pk_index", unique = true, columnList = "id")})
public class Feedback extends BaseDomain {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id = 0;

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "message")
  @Size(min = 1, max = 1000)
  private String message;

  @Version
  @Column(name = "version_number")
  private long versionNumber = 0;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(long versionNumber) {
    this.versionNumber = versionNumber;
  }

  @Override
  public String toString() {
    return "Feedback [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + ", versionNumber="
        + versionNumber + "]";
  }



}
