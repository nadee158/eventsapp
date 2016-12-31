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
@Table(name = "team",
    indexes = {@Index(name = "team_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "team_id_index", unique = true, columnList = "uuid"),
        @Index(name = "team_name_index", unique = true, columnList = "team_name")})
public class Team extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "team_name")
  private String teamName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }



}
