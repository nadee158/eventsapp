package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.core.CategorySetupItemDTO;

public class CategoryCreationRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private String categorySetupName = StringUtils.EMPTY;

  private long ageGroupId;

  private String gender;

  private String gradeOrBelt = StringUtils.EMPTY;

  private long eventId;

  private List<CategorySetupItemDTO> categorySetupItems;

  public String getCategorySetupName() {
    return categorySetupName;
  }

  public void setCategorySetupName(String categorySetupName) {
    this.categorySetupName = categorySetupName;
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

  public long getAgeGroupId() {
    return ageGroupId;
  }

  public void setAgeGroupId(long ageGroupId) {
    this.ageGroupId = ageGroupId;
  }

  @Override
  public String toString() {
    return "CategoryCreationRequest [categorySetupName=" + categorySetupName + ", ageGroupId=" + ageGroupId
        + ", gender=" + gender + ", gradeOrBelt=" + gradeOrBelt + ", eventId=" + eventId + ", categorySetupItems="
        + categorySetupItems + "]";
  }



}
