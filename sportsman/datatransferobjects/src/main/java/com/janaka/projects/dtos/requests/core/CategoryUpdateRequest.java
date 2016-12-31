package com.janaka.projects.dtos.requests.core;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.core.CategorySetupItemDTO;

public class CategoryUpdateRequest implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private long id;

  private String categorySetupName = StringUtils.EMPTY;

  private long ageGroupId;

  private String gender;

  private String gradeOrBelt = StringUtils.EMPTY;

  private long eventId;

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

  public long getAgeGroupId() {
    return ageGroupId;
  }

  public void setAgeGroupId(long ageGroupId) {
    this.ageGroupId = ageGroupId;
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

  public String getRecordStatus() {
    return recordStatus;
  }

  public void setRecordStatus(String recordStatus) {
    this.recordStatus = recordStatus;
  }



}
