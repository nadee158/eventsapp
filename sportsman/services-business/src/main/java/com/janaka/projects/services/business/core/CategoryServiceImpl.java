package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.CategoryCreationRequest;
import com.janaka.projects.dtos.requests.core.CategoryUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.CategoryCreationResponse;
import com.janaka.projects.dtos.responses.core.CategoryUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.AgeGroupRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.CategorySetupRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.EventRepository;
import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.CategorySetupDTOConverter;
import com.janaka.projects.services.core.CategoryService;

@Service("categoryService")
@Transactional()
public class CategoryServiceImpl extends BusinessService implements CategoryService {

  @Autowired
  private EventRepository eventRepository;

  @Autowired
  private AgeGroupRepository ageGroupRepository;

  @Autowired
  private CategorySetupRepository categorySetupRepository;



  @Override
  public CategoryCreationResponse createEvent(CategoryCreationRequest request) {
    System.out.println("request " + request);
    Event event = eventRepository.findOne(request.getEventId());
    AgeGroup ageGroup = ageGroupRepository.findOne(request.getAgeGroupId());
    CategorySetup categorySetup = CategorySetupDTOConverter.convertRequestToDomain(request, event, ageGroup);
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
    CategorySetup categorySetupFromDb = categorySetupRepository.findOne(request.getId());
    if (!(categorySetupFromDb == null)) {
      System.out.println("request " + request);
      Event event = eventRepository.findOne(request.getEventId());
      AgeGroup ageGroup = ageGroupRepository.findOne(request.getAgeGroupId());

      categorySetupFromDb =
          CategorySetupDTOConverter.updateDomainFromRequest(request, categorySetupFromDb, event, ageGroup);
      CategorySetup updatedCategorySetup = categorySetupRepository.save(categorySetupFromDb);
      CategoryUpdateResponse response = new CategoryUpdateResponse();
      response.setCategoryDTO(CategorySetupDTOConverter.convertDomainToDTO(updatedCategorySetup));
      response.setId(updatedCategorySetup.getId());
      response.setMessage("UPDATED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public ObjectDeletionResponse deleteCategory(ObjectDeletionRequest request) {
    CategorySetup categorySetupFromDb = categorySetupRepository.findOne(request.getId());
    if (!(categorySetupFromDb == null)) {
      categorySetupFromDb.setRecordStatus(RecordStatus.INACTIVE);
      CategorySetup updatedCategorySetup = categorySetupRepository.save(categorySetupFromDb);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setId(updatedCategorySetup.getId());
      response.setMessage("DELETED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public ObjectListResponse<CategoryDTO> getAllActiveCategories() {
    List<CategorySetup> activeCategories = categorySetupRepository.findByRecordStatus(RecordStatus.ACTIVE);
    System.out.println("activeEvents :" + activeCategories);
    if (!(activeCategories == null || activeCategories.isEmpty())) {
      ObjectListResponse<CategoryDTO> response = new ObjectListResponse<CategoryDTO>();
      List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
      activeCategories.forEach(event -> {
        dtoList.add(CategorySetupDTOConverter.convertDomainToDTO(event));
      });
      response.setDtoList(dtoList);
      response.setListSize(dtoList.size());
      response.setMessage("LISTED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    }
    return null;
  }

  @Override
  public ObjectRetrievalResponse<CategoryDTO> getCategoryById(ObjectRetrievalRequest request) {
    CategorySetup categorySetupFromDb = categorySetupRepository.findOne(request.getId());
    if (!(categorySetupFromDb == null)) {
      ObjectRetrievalResponse<CategoryDTO> response = new ObjectRetrievalResponse<CategoryDTO>();
      response.setDto(CategorySetupDTOConverter.convertDomainToDTO(categorySetupFromDb));
      response.setId(categorySetupFromDb.getId());
      response.setMessage("RETRIEVED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Event was not found!");
    }
  }

  @Override
  public TabularDataResponseModel<CategoryDTO> getCategories(TabularDataRequestModel request) {
    DataTablesOutput<CategorySetup> domainResponse = categorySetupRepository.findAll(request);
    TabularDataResponseModel<CategoryDTO> response = new TabularDataResponseModel<CategoryDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
        domainResponse.getData().forEach(event -> {
          dtoList.add(CategorySetupDTOConverter.convertDomainToDTO(event));
        });
        response.setData(dtoList);
      }
      response.setDraw(domainResponse.getDraw());
      response.setError(domainResponse.getError());
      response.setRecordsFiltered(domainResponse.getRecordsFiltered());
      response.setRecordsTotal(domainResponse.getRecordsTotal());
    }
    return response;
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
