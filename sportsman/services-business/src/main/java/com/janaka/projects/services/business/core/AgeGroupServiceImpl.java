package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.AgeGroupDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupCreationRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupCreationResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupUpdateResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.AgeGroupRepository;
import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.AgeGroupDTOConverter;
import com.janaka.projects.services.core.AgeGroupService;

@Service("ageGroupService")
public class AgeGroupServiceImpl extends BusinessService implements AgeGroupService {

  @Autowired
  private AgeGroupRepository ageGroupRepository;


  @Override
  public AgeGroupCreationResponse createAgeGroup(AgeGroupCreationRequest request) {
    AgeGroup ageGroup = AgeGroupDTOConverter.convertRequestToDomain(request);
    AgeGroup persisted = ageGroupRepository.save(ageGroup);
    AgeGroupCreationResponse response = new AgeGroupCreationResponse();
    response.setAgeGroupDTO(AgeGroupDTOConverter.convertDomainToDTO(persisted));
    response.setId(persisted.getId());
    response.setMessage("SAVED_SUCCESSFULLY");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }

  @Override
  public AgeGroupUpdateResponse updateAgeGroup(AgeGroupUpdateRequest request) {
    AgeGroup ageGroup = ageGroupRepository.findOne(request.getId());
    if (!(ageGroup == null)) {
      ageGroup = AgeGroupDTOConverter.updateDomainFromRequest(request, ageGroup);
      AgeGroup updatedAgeGroup = ageGroupRepository.save(ageGroup);
      AgeGroupUpdateResponse response = new AgeGroupUpdateResponse();
      response.setAgeGroupDTO(AgeGroupDTOConverter.convertDomainToDTO(updatedAgeGroup));
      response.setId(updatedAgeGroup.getId());
      response.setMessage("UPDATED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("AgeGroup was not found!");
    }
  }


  @Override
  public ObjectDeletionResponse deleteAgeGroup(ObjectDeletionRequest request) {
    AgeGroup ageGroupFromDb = ageGroupRepository.findOne(request.getId());
    if (!(ageGroupFromDb == null)) {
      ageGroupFromDb.setRecordStatus(RecordStatus.INACTIVE);
      AgeGroup updatedAgeGroup = ageGroupRepository.save(ageGroupFromDb);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setId(updatedAgeGroup.getId());
      response.setMessage("DELETED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Age Group was not found!");
    }
  }

  @Override
  public ObjectListResponse<AgeGroupDTO> getAllActiveAgeGroups() {
    List<AgeGroup> activeAgeGroups = ageGroupRepository.findByRecordStatus(RecordStatus.ACTIVE);
    System.out.println("activeAgeGroups :" + activeAgeGroups);
    if (!(activeAgeGroups == null || activeAgeGroups.isEmpty())) {
      ObjectListResponse<AgeGroupDTO> response = new ObjectListResponse<AgeGroupDTO>();
      List<AgeGroupDTO> dtoList = new ArrayList<AgeGroupDTO>();
      activeAgeGroups.forEach(event -> {
        dtoList.add(AgeGroupDTOConverter.convertDomainToDTO(event));
      });
      java.util.Collections.sort(dtoList, new Comparator<AgeGroupDTO>() {
        @Override
        public int compare(AgeGroupDTO o1, AgeGroupDTO o2) {
          if (o1.getFromAge() > o2.getFromAge()) {
            return 1;
          } else if (o1.getFromAge() < o2.getFromAge()) {
            return -1;
          } ;
          return 0;
        }
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
  public ObjectRetrievalResponse<AgeGroupDTO> getAgeGroupById(ObjectRetrievalRequest request) {
    AgeGroup ageGroupFromDb = ageGroupRepository.findOne(request.getId());
    if (!(ageGroupFromDb == null)) {
      ObjectRetrievalResponse<AgeGroupDTO> response = new ObjectRetrievalResponse<AgeGroupDTO>();
      response.setDto(AgeGroupDTOConverter.convertDomainToDTO(ageGroupFromDb));
      response.setId(ageGroupFromDb.getId());
      response.setMessage("RETRIEVED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("AgeGroup was not found!");
    }
  }

  @Override
  public TabularDataResponseModel<AgeGroupDTO> getAgeGroups(TabularDataRequestModel request) {
    DataTablesOutput<AgeGroup> domainResponse = ageGroupRepository.findAll(request);
    TabularDataResponseModel<AgeGroupDTO> response = new TabularDataResponseModel<AgeGroupDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<AgeGroupDTO> dtoList = new ArrayList<AgeGroupDTO>();
        domainResponse.getData().forEach(event -> {
          dtoList.add(AgeGroupDTOConverter.convertDomainToDTO(event));
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


}
