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

  private int fromAge;

  private int toAge;

  private String gender;

  private String gradeOrBelt = StringUtils.EMPTY;

  private long eventId;

  private List<CategorySetupItemDTO> categorySetupItems;

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

  public String getGradeOrBelt() {
    return gradeOrBelt;
  }

  public void setGradeOrBelt(String gradeOrBelt) {
    this.gradeOrBelt = gradeOrBelt;
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



}
