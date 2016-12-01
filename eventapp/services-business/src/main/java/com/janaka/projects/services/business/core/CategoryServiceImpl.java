package com.janaka.projects.services.business.core;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.core.CategoryService;

@Service("categoryService")
@Transactional()
public class CategoryServiceImpl extends BusinessService implements CategoryService {

  @Override
  public CategoryCreationResponse createEvent(CategoryCreationRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public CategoryUpdateResponse updateEvent(CategoryUpdateRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectDeletionResponse deleteCategory(ObjectDeletionRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectListResponse<CategoryDTO> getAllActiveCategories() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjectRetrievalResponse<CategoryDTO> getCategoryById(ObjectRetrievalRequest request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public TabularDataResponseModel<CategoryDTO> getCategories(TabularDataRequestModel request) {
    // TODO Auto-generated method stub
    return null;
  }

}
