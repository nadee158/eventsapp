package com.janaka.projects.entitymanagement.domain.core;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.Audited;

import com.janaka.projects.entitymanagement.domain.common.AuditEntity;
import com.janaka.projects.entitymanagement.enums.Gender;

@Audited
@Entity
@Table(name = "category_setup", indexes = {
    @Index(name = "category_setup_id_pk_index", unique = true, columnList = "id"),
    @Index(name = "category_setup_id_index", unique = true, columnList = "uuid"),
    @Index(name = "category_setup_name_status_index", unique = true, columnList = "category_setup_name,record_status")})
public class CategorySetup extends AuditEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Column(name = "category_setup_name")
  private String categorySetupName = StringUtils.EMPTY;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "age_group_id", referencedColumnName = "id", nullable = false)
  private AgeGroup ageGroup;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender;

  @Column(name = "grade_or_belt")
  private String gradeOrBelt = StringUtils.EMPTY;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
  private Event event;

  @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
  private List<CategorySetupItem> categorySetupItems;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCategorySetupName() {
    return categorySetupName;
  }

  public void setCategorySetupName(String categorySetupName) {
    this.categorySetupName = categorySetupName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public String getGradeOrBelt() {
    return gradeOrBelt;
  }

  public void setGradeOrBelt(String gradeOrBelt) {
    this.gradeOrBelt = gradeOrBelt;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }

  public List<CategorySetupItem> getCategorySetupItems() {
    return categorySetupItems;
  }

  public void setCategorySetupItems(List<CategorySetupItem> categorySetupItems) {
    this.categorySetupItems = categorySetupItems;
  }

  public AgeGroup getAgeGroup() {
    return ageGroup;
  }

  public void setAgeGroup(AgeGroup ageGroup) {
    this.ageGroup = ageGroup;
  }

  @Override
  public String toString() {
    return "CategorySetup [id=" + id + ", categorySetupName=" + categorySetupName + ", ageGroup=" + ageGroup
        + ", gender=" + gender + ", gradeOrBelt=" + gradeOrBelt + ", event=" + event + ", categorySetupItems="
        + categorySetupItems + "]";
  }



}
