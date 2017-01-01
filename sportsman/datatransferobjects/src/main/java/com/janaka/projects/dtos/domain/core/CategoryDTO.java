package com.janaka.projects.dtos.domain.core;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class CategoryDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String categorySetupName = StringUtils.EMPTY;

  private long ageGroupId;

  public long getAgeGroupId() {
    return ageGroupId;
  }

  public void setAgeGroupId(long ageGroupId) {
    this.ageGroupId = ageGroupId;
  }

  private int fromAge;

  private int toAge;

  private String gender;

  private long gradeOrBeltId;

  private String gradeOrBeltName = StringUtils.EMPTY;

  private long eventId;

  private String eventName;

  private List<CategorySetupItemDTO> categorySetupItems;

  private String recordStatus;

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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }


  public long getGradeOrBeltId() {
    return gradeOrBeltId;
  }

  public void setGradeOrBeltId(long gradeOrBeltId) {
    this.gradeOrBeltId = gradeOrBeltId;
  }

  public String getGradeOrBeltName() {
    return gradeOrBeltName;
  }

  public void setGradeOrBeltName(String gradeOrBeltName) {
    this.gradeOrBeltName = gradeOrBeltName;
  }

  public long getEventId() {
    return eventId;
  }

  public void setEventId(long eventId) {
    this.eventId = eventId;
  }

  public List<CategorySetupItemDTO> getCategorySetupItems() {
    return categorySetupItems;
  }

  public void setCategorySetupItems(List<CategorySetupItemDTO> categorySetupItems) {
    this.categorySetupItems = categorySetupItems;
  }

  public String getEventName() {
    return eventName;
  }

  public void setEventName(String eventName) {
    this.eventName = eventName;
  }

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }



}
