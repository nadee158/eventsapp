package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
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
import com.janaka.projects.entitymanagement.dataaccessobjects.core.CategorySetupRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.EventRepository;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.CategorySetupDTOConverter;
import com.janaka.projects.services.core.CategoryService;

@Service("categoryService")
@Transactional()
public class CategoryServiceImpl extends BusinessService implements CategoryService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private CategorySetupRepository categorySetupRepository;

  @Override
  public CategoryCreationResponse createEvent(CategoryCreationRequest request) {
    System.out.println("request " + request);
    Event event = eventRepository.findOne(request.getEventId());
    CategorySetup categorySetup = CategorySetupDTOConverter.convertRequestToDomain(request, event);
    CategorySetup persisted = categorySetupRepository.save(categorySetup);
    CategoryCreationResponse response = new CategoryCreationResponse();
    response.setCategoryDTO(CategorySetupDTOConverter.convertDomainToDTO(persisted));
    response.setId(persisted.getId());
    response.setMessage("SAVED_SUCCESSFULLY");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
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

  @Override
  public ObjectListResponse<CategoryDTO> getCategoryByEventId(ObjectRetrievalRequest request) {
    List<CategorySetup> categorySetupList = categorySetupRepository.findByEvent_Id(request.getId());
    if (!(categorySetupList == null || categorySetupList.isEmpty())) {
      ObjectListResponse<CategoryDTO> response = new ObjectListResponse<CategoryDTO>();
      response.setDtoList(new ArrayList<CategoryDTO>());
      categorySetupList.forEach(item -> {
        response.getDtoList().add(CategorySetupDTOConverter.convertDomainToDTO(item));
      });
      response.setListSize(response.getDtoList().size());
      response.setMessage("SUCCESS");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

}
