package com.janaka.projects.dtos.requests.usermanagement.mock;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.requests.usermanagement.UserRoleCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleUpdateRequest;

public class MockUserRoleUtil {



  public static UserRoleCreationRequest getCreateUserRoleRequest() {
    UserRoleCreationRequest request = new UserRoleCreationRequest();
    request.setUserRoleName("ROLE_SUPER_ADMIN");
    return request;
  }


  public static UserRoleUpdateRequest getUserRoleUpdateRequest() {
    UserRoleUpdateRequest request = new UserRoleUpdateRequest();
    request.setDeleted(false);
    request.setId(3);
    request.setUserRoleName("Test User Role Name Modified");
    return request;
  }

  public static List<UserRoleCreationRequest> getCreateUserRoleRequestList() {
    List<UserRoleCreationRequest> list = new ArrayList<UserRoleCreationRequest>();
    list.add(getCreateUserRoleRequest("Usermanagement Super Admin"));
    return list;
  }

  private static UserRoleCreationRequest getCreateUserRoleRequest(String userRoleName) {
    UserRoleCreationRequest request = new UserRoleCreationRequest();
    request.setUserRoleName(userRoleName);
    return request;
  }


}
