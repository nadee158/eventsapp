package com.janaka.projects.entitymanagement.domain.core;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.janaka.projects.entitymanagement.domain.common.AuditEntity;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;

@Audited
@Entity
@Table(name = "player",
    indexes = {@Index(name = "player_id_pk_index", unique = true, columnList = "id"),
        @Index(name = "player_id_index", unique = true, columnList = "uuid"),
        @Index(name = "player_number_index", unique = true, columnList = "player_number")})
public class Player extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "player_number")
  private String playerNumber = StringUtils.EMPTY;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
  private Team team;

  @Column(name = "height")
  private double height;

  @Column(name = "weight")
  private double weight;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
  private CategorySetup categorySetup;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
  private Person person;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPlayerNumber() {
    return playerNumber;
  }

  public void setPlayerNumber(String playerNumber) {
    this.playerNumber = playerNumber;
  }


  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public double getHeight() {
    return height;
  }

  public void setHeight(double height) {
    this.height = height;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public CategorySetup getCategorySetup() {
    return categorySetup;
  }

  public void setCategorySetup(CategorySetup categorySetup) {
    this.categorySetup = categorySetup;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public String toString() {
    return "Player [id=" + id + ", playerNumber=" + playerNumber + ", team=" + team + ", height=" + height + ", weight="
        + weight + ", categorySetup=" + categorySetup + ", person=" + person + "]";
  }



}
