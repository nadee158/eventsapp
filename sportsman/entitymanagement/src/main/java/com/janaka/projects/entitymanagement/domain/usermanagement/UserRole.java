package com.janaka.projects.entitymanagement.domain.usermanagement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.janaka.projects.entitymanagement.domain.common.AuditEntity;

@Audited
@Entity
@Table(name = "user_role",
    indexes = {@Index(name = "user_role_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "user_role_id_index", unique = true, columnList = "uuid"),
        @Index(name = "user_role_name_index", unique = true, columnList = "user_role_name")})
public class UserRole extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id = 0;

  @NotNull
  @Column(name = "user_role_name")
  private String userRoleName = StringUtils.EMPTY;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserRoleName() {
    return userRoleName;
  }

  public void setUserRoleName(String userRoleName) {
    this.userRoleName = userRoleName;
  }

  @Override
  public String toString() {
    return "Event [id=" + id + ", userRoleName=" + userRoleName + "]";
  }



}
