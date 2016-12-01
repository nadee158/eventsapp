package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.CategoryCreationRequest;
import com.janaka.projects.dtos.requests.core.CategoryUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.CategoryCreationResponse;
import com.janaka.projects.dtos.responses.core.CategoryUpdateResponse;

public interface CategoryService {

  public CategoryCreationResponse createEvent(CategoryCreationRequest request);

  public CategoryUpdateResponse updateEvent(CategoryUpdateRequest request);

  public ObjectDeletionResponse deleteCategory(ObjectDeletionRequest request);

  public ObjectListResponse<CategoryDTO> getAllActiveCategories();

  public ObjectRetrievalResponse<CategoryDTO> getCategoryById(ObjectRetrievalRequest request);

  public TabularDataResponseModel<CategoryDTO> getCategories(TabularDataRequestModel request);

}
