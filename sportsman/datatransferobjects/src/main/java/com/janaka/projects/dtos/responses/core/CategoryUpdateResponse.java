package com.janaka.projects.dtos.responses.core;

import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class CategoryUpdateResponse extends BaseResponse {

  private static final long serialVersionUID = 1L;

  private long id;

  private CategoryDTO categoryDTO;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public CategoryDTO getCategoryDTO() {
    return categoryDTO;
  }

  public void setCategoryDTO(CategoryDTO categoryDTO) {
    this.categoryDTO = categoryDTO;
  }



}
