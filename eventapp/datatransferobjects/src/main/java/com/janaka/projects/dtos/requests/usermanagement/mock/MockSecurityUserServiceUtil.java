package com.janaka.projects.dtos.requests.usermanagement.mock;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.dtos.requests.usermanagement.SecurityUserCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserUpdateRequest;

public class MockSecurityUserServiceUtil {

  public static SecurityUserCreationRequest getSecurityUserCreationRequest() {
    SecurityUserCreationRequest request = new SecurityUserCreationRequest();
    request.setAddress("Test Address");
    request.setEmail("nadee158@gmail.com");
    request.setFullName("Super user Testing");
    request.setMobileNumber("0712186182");
    request.setNic("865480433v");
    request.setPrefixCode(1);
    request.setSecret("eventsappuser@123");
    request.setUserName("eventsappuser");
    List<Long> urlist = new ArrayList<Long>();
    urlist.add(1l);
    request.setUserRoles(urlist);
    return request;
  }


  private static List<Long> getUserRoles() {
    List<Long> list = new ArrayList<Long>();
    for (long i = 3; i < 6; i++) {
      list.add(i);
    }
    return list;
  }

  public static SecurityUserUpdateRequest getSecurityUserUpdateRequest() {
    SecurityUserUpdateRequest request = new SecurityUserUpdateRequest();
    request.setId(1);
    request.setAddress("Test Address modified");
    request.setEmail("abc@gmail.com");
    request.setFullName("User full name testing fv ");
    request.setMobileNumber("0712186182");
    request.setNic("865480483v");
    request.setPrefixCode(1);
    request.setUserName("nadee158");
    request.setVersionNumber(1);
    return request;
  }

  public static List<SecurityUserCreationRequest> getSecurityUserCreationRequestList() {
    List<SecurityUserCreationRequest> list = new ArrayList<SecurityUserCreationRequest>();
    SecurityUserCreationRequest request = new SecurityUserCreationRequest();
    request.setAddress("Pwc Sri Lanka");
    request.setEmail("umgtadmin@gmail.com");
    request.setFullName("User Management Super Administrator");
    request.setMobileNumber("0771234560");
    request.setNic("123456789V");
    request.setPrefixCode(1);
    request.setSecret("umgtadmin@123");
    request.setUserName("umgtadmin");
    List<Long> urlist = new ArrayList<Long>();
    urlist.add(1l);
    request.setUserRoles(urlist);
    list.add(request);
    return list;
  }



}
