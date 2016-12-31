package com.janaka.projects.services.business.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.GradeBeltDTO;
import com.janaka.projects.dtos.exceptions.ResultsNotFoundException;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.GradeBeltResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.core.GradeBeltRepository;
import com.janaka.projects.entitymanagement.domain.core.GradeBelt;
import com.janaka.projects.entitymanagement.enums.RecordStatus;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.domaindtoconverter.core.GradeBeltDTOConverter;
import com.janaka.projects.services.core.GradeBeltService;

@Service("gradeBeltService")
public class GradeBeltServiceImpl extends BusinessService implements GradeBeltService {

  @Autowired
  private GradeBeltRepository gradeBeltRepository;

  @Override
  public GradeBeltResponse createGradeBelt(GradeBeltDTO request) {
    GradeBelt gradeBelt = GradeBeltDTOConverter.convertRequestToDomain(request);
    GradeBelt persisted = gradeBeltRepository.save(gradeBelt);
    GradeBeltResponse response = new GradeBeltResponse();
    response.setGradeBeltDTO(GradeBeltDTOConverter.convertDomainToDTO(persisted));
    response.setId(persisted.getId());
    response.setMessage("SAVED_SUCCESSFULLY");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }

  @Override
  public GradeBeltResponse updateGradeBelt(GradeBeltDTO request) {
    GradeBelt gradeBelt = gradeBeltRepository.findOne(request.getId());
    if (!(gradeBelt == null)) {
      gradeBelt = GradeBeltDTOConverter.updateDomainFromRequest(request, gradeBelt);
      GradeBelt updatedGradeBelt = gradeBeltRepository.save(gradeBelt);
      GradeBeltResponse response = new GradeBeltResponse();
      response.setGradeBeltDTO(GradeBeltDTOConverter.convertDomainToDTO(updatedGradeBelt));
      response.setId(updatedGradeBelt.getId());
      response.setMessage("UPDATED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("GradeBelt was not found!");
    }
  }

  @Override
  public ObjectDeletionResponse deleteGradeBelt(ObjectDeletionRequest request) {
    GradeBelt gradeBeltFromDb = gradeBeltRepository.findOne(request.getId());
    if (!(gradeBeltFromDb == null)) {
      gradeBeltFromDb.setRecordStatus(RecordStatus.INACTIVE);
      GradeBelt updatedGradeBelt = gradeBeltRepository.save(gradeBeltFromDb);
      ObjectDeletionResponse response = new ObjectDeletionResponse();
      response.setId(updatedGradeBelt.getId());
      response.setMessage("DELETED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("Age Group was not found!");
    }
  }

  @Override
  public ObjectListResponse<GradeBeltDTO> getAllActiveGradeBelts() {
    List<GradeBelt> activeGradeBelts = gradeBeltRepository.findByRecordStatus(RecordStatus.ACTIVE);
    System.out.println("activeGradeBelts :" + activeGradeBelts);
    if (!(activeGradeBelts == null || activeGradeBelts.isEmpty())) {
      ObjectListResponse<GradeBeltDTO> response = new ObjectListResponse<GradeBeltDTO>();
      List<GradeBeltDTO> dtoList = new ArrayList<GradeBeltDTO>();
      activeGradeBelts.forEach(event -> {
        dtoList.add(GradeBeltDTOConverter.convertDomainToDTO(event));
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
  public ObjectRetrievalResponse<GradeBeltDTO> getGradeBeltById(ObjectRetrievalRequest request) {
    GradeBelt gradeBeltFromDb = gradeBeltRepository.findOne(request.getId());
    if (!(gradeBeltFromDb == null)) {
      ObjectRetrievalResponse<GradeBeltDTO> response = new ObjectRetrievalResponse<GradeBeltDTO>();
      response.setDto(GradeBeltDTOConverter.convertDomainToDTO(gradeBeltFromDb));
      response.setId(gradeBeltFromDb.getId());
      response.setMessage("RETRIEVED_SUCCESSFULLY");
      response.setStatus(ApplicationConstants.STATUS_CODE_OK);
      return response;
    } else {
      throw new ResultsNotFoundException("GradeBelt was not found!");
    }
  }

  @Override
  public TabularDataResponseModel<GradeBeltDTO> getGradeBelts(TabularDataRequestModel request) {
    DataTablesOutput<GradeBelt> domainResponse = gradeBeltRepository.findAll(request);
    TabularDataResponseModel<GradeBeltDTO> response = new TabularDataResponseModel<GradeBeltDTO>();
    if (!(domainResponse == null)) {
      if (!(domainResponse.getData() == null || domainResponse.getData().isEmpty())) {
        List<GradeBeltDTO> dtoList = new ArrayList<GradeBeltDTO>();
        domainResponse.getData().forEach(event -> {
          dtoList.add(GradeBeltDTOConverter.convertDomainToDTO(event));
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
