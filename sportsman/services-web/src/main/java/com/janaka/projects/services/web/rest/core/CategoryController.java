package com.janaka.projects.services.web.rest.core;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.CategoryCreationRequest;
import com.janaka.projects.dtos.requests.core.CategoryUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.CategoryCreationResponse;
import com.janaka.projects.dtos.responses.core.CategoryUpdateResponse;
import com.janaka.projects.services.core.CategoryService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.CATEGORIES)
public class CategoryController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private CategoryService categoryService;

  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomResponseEntity<ApiResponseObject<?>> createCategories(
      @Valid @RequestBody CategoryCreationRequest request) {
    System.out.println("CategoryCreationRequest " + request);
    CategoryCreationResponse response = categoryService.createEvent(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<CategoryCreationResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updateCategory(@Valid @RequestBody CategoryUpdateRequest request) {
    CategoryUpdateResponse response = categoryService.updateEvent(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<CategoryUpdateResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> deleteEvent(@RequestBody ObjectDeletionRequest request) {
    ObjectDeletionResponse response = categoryService.deleteCategory(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<CategoryDTO> response = categoryService.getAllActiveCategories();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<CategoryDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCategoryById(@RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<CategoryDTO> response = categoryService.getCategoryById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<CategoryDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<CategoryDTO> getActiveCategories(@RequestBody TabularDataRequestModel request) {
    return categoryService.getCategories(request);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_EVENT_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCategoryByEventId(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectListResponse<CategoryDTO> response = categoryService.getCategoryByEventId(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<CategoryDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.COUNT, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCount() {
    long count = categoryService.getActiveCount();
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<Long>(HttpStatus.OK, count),
        HttpStatus.OK);
  }


}
