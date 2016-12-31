package com.janaka.projects.entitymanagement.domain.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import com.janaka.projects.entitymanagement.domain.common.AuditEntity;

@Audited
@Entity
@Table(name = "grade_belt",
    indexes = {@Index(name = "grade_belt_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "grade_belt_id_index", unique = true, columnList = "uuid"),
        @Index(name = "grade_belt_name_index", unique = true, columnList = "grade_belt_name")})
public class GradeBelt extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "grade_belt_name")
  private String gradeBeltName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getGradeBeltName() {
    return gradeBeltName;
  }

  public void setGradeBeltName(String gradeBeltName) {
    this.gradeBeltName = gradeBeltName;
  }

  @Override
  public String toString() {
    return "GradeBelt [id=" + id + ", gradeBeltName=" + gradeBeltName + "]";
  }



}
