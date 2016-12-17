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
@Table(name = "age_group",
    indexes = {@Index(name = "age_group_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "age_group_id_index", unique = true, columnList = "uuid"),
        @Index(name = "age_group_from_to_index", unique = true, columnList = "from_age,toAge")})
public class AgeGroup extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "from_age")
  private int fromAge;

  @Column(name = "to_age")
  private int toAge;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public int getFromAge() {
    return fromAge;
  }

  public void setFromAge(int fromAge) {
    this.fromAge = fromAge;
  }

  public int getToAge() {
    return toAge;
  }

  public void setToAge(int toAge) {
    this.toAge = toAge;
  }

  @Override
  public String toString() {
    return "AgeGroup [id=" + id + ", fromAge=" + fromAge + ", toAge=" + toAge + "]";
  }



}
