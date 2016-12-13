package com.janaka.projects.services.reports.dto;

public class ReportParamDTO {

  private long id = 0;

  private String name;

  private String type;

  private String value;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "ReportParamRequest [id=" + id + ", name=" + name + ", type=" + type + ", value=" + value + "]";
  }

}
