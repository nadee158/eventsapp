package com.janaka.projects.services.core;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.GradeBeltDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.GradeBeltResponse;

public interface GradeBeltService {

  public GradeBeltResponse createGradeBelt(GradeBeltDTO request);

  public GradeBeltResponse updateGradeBelt(GradeBeltDTO request);

  public ObjectDeletionResponse deleteGradeBelt(ObjectDeletionRequest request);

  public ObjectListResponse<GradeBeltDTO> getAllActiveGradeBelts();

  public ObjectRetrievalResponse<GradeBeltDTO> getGradeBeltById(ObjectRetrievalRequest request);

  public TabularDataResponseModel<GradeBeltDTO> getGradeBelts(TabularDataRequestModel request);

  public long getActiveCount();
}
