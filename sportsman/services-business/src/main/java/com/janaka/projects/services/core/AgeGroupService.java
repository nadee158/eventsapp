package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.AgeGroupDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupCreationRequest;
import com.janaka.projects.dtos.requests.core.AgeGroupUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupCreationResponse;
import com.janaka.projects.dtos.responses.core.AgeGroupUpdateResponse;

public interface AgeGroupService {

  public AgeGroupCreationResponse createAgeGroup(AgeGroupCreationRequest request);

  public AgeGroupUpdateResponse updateAgeGroup(AgeGroupUpdateRequest request);

  public ObjectDeletionResponse deleteAgeGroup(ObjectDeletionRequest request);

  public ObjectListResponse<AgeGroupDTO> getAllActiveAgeGroups();

  public ObjectRetrievalResponse<AgeGroupDTO> getAgeGroupById(ObjectRetrievalRequest request);

  public TabularDataResponseModel<AgeGroupDTO> getAgeGroups(TabularDataRequestModel request);
}
