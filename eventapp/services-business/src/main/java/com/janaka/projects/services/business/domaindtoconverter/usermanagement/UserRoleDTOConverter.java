package com.janaka.projects.services.business.domaindtoconverter.usermanagement;

import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.usermanagement.UserRoleCreationRequest;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class UserRoleDTOConverter {

  public static UserRole convertDTOToDomain(UserRoleDTO userRoleDTO) {
    if (!(userRoleDTO == null)) {
      UserRole userRole = new UserRole();
      userRole.setRecordStatus(RecordStatus.fromRecordStatusCode(userRoleDTO.getRecordStatus()));
      userRole.setVersion(userRoleDTO.getVersionNumber());
      return userRole;
    }
    return null;
  }

  public static UserRoleDTO convertDomainToDTO(UserRole userRole) {
    if (!(userRole == null)) {
      UserRoleDTO userRoleDTO = new UserRoleDTO();
      userRoleDTO.setCreatedByUser(userRole.getCreatedByUser());
      userRoleDTO.setCreationTime(userRole.getCreationTime());
      userRoleDTO.setRecordStatus(userRole.getRecordStatus().getRecordStatusCode());
      userRoleDTO.setId(userRole.getId());
      userRoleDTO.setModificationTime(userRole.getModificationTime());
      userRoleDTO.setModifiedByUser(userRole.getModifiedByUser());
      userRoleDTO.setUserRoleName(userRole.getUserRoleName());
      userRoleDTO.setUuId(userRole.getUuId());
      userRoleDTO.setVersionNumber(userRole.getVersion());
      return userRoleDTO;
    }
    return null;
  }

  public static UserRole convertRequestToDomain(UserRoleCreationRequest request) {
    UserRole userRole = new UserRole();
    userRole.setUserRoleName(request.getUserRoleName());
    userRole.setRecordStatus(RecordStatus.ACTIVE);
    return userRole;
  }

}
