package com.janaka.projects.dtos.requests.usermanagement.mock;

import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;

public class MockObjectDeletionRequestUtil {

  public static ObjectDeletionRequest getObjectDeletionRequest() {
    ObjectDeletionRequest request = new ObjectDeletionRequest();
    request.setId(1);
    request.setVersionNumber(1);
    return request;
  }

}
