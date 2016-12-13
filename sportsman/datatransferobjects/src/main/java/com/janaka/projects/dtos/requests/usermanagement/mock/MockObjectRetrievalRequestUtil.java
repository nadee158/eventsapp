package com.janaka.projects.dtos.requests.usermanagement.mock;

import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;

public class MockObjectRetrievalRequestUtil {

  public static ObjectRetrievalRequest getObjectRetrievalRequest() {
    ObjectRetrievalRequest request = new ObjectRetrievalRequest();
    request.setId(1);
    return request;
  }

}
