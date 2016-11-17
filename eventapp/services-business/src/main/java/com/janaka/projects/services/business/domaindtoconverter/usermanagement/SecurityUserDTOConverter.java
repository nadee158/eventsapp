package com.janaka.projects.services.business.domaindtoconverter.usermanagement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.janaka.projects.dtos.domain.usermanagement.MaritalStatusDTO;
import com.janaka.projects.dtos.domain.usermanagement.PersonDTO;
import com.janaka.projects.dtos.domain.usermanagement.PrefixDTO;
import com.janaka.projects.dtos.domain.usermanagement.SecurityUserDTO;
import com.janaka.projects.dtos.domain.usermanagement.UserRoleDTO;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserCreationRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserPermissionUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserProfileUpdateRequest;
import com.janaka.projects.dtos.requests.usermanagement.SecurityUserUpdateRequest;
import com.janaka.projects.entitymanagement.dataaccessobjects.usermanagement.UserRoleRepository;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;
import com.janaka.projects.entitymanagement.domain.usermanagement.SecurityUser;
import com.janaka.projects.entitymanagement.domain.usermanagement.UserRole;
import com.janaka.projects.entitymanagement.enums.MaritalStatus;
import com.janaka.projects.entitymanagement.enums.Prefix;

public class SecurityUserDTOConverter {

  public static SecurityUser convertDTOToDomain(SecurityUserDTO securityUserDTO,
      UserRoleRepository userRoleRepository) {
    if (!(securityUserDTO == null)) {
      SecurityUser securityUser = new SecurityUser();
      Person person = getUpdatedPerson(new Person(), securityUserDTO.getPerson());
      securityUser.setDeleted(securityUserDTO.isDeleted());
      securityUser.setPerson(person);
      securityUser.setSecret(securityUserDTO.getSecret());
      securityUser.setUserName(securityUserDTO.getUserName());

      if (!(userRoleRepository == null)) {
        if (!(securityUserDTO.getUserRoles() == null || securityUserDTO.getUserRoles().isEmpty())) {
          Set<UserRole> userRoles = new HashSet<UserRole>();
          for (UserRoleDTO userRoleDTO : securityUserDTO.getUserRoles()) {
            if (!(userRoleDTO.getId() <= 0)) {
              userRoles.add(userRoleRepository.findOne(userRoleDTO.getId()));
            }
          }
          securityUser.setUserRoles(userRoles);
        }
      }

      securityUser.setVersionNumber(securityUserDTO.getVersionNumber());

      return securityUser;
    }
    return null;
  }

  private static Person getUpdatedPerson(Person person, PersonDTO personDTO) {
    person.setDateOfBirth(personDTO.getDateOfBirth());
    person.setEmail(personDTO.getEmail());
    person.setFullName(personDTO.getFullName());
    if (!(personDTO.getMaritalStatus() == null || personDTO.getMaritalStatus().getCode() != 0)) {
      person.setMaritalStatus(MaritalStatus.fromCode(personDTO.getMaritalStatus().getCode()));
    }
    person.setProfileImagePath(personDTO.getProfileImagePath());
    person.setAddress(personDTO.getAddress());
    person.setMobileNumber(personDTO.getMobileNumber());
    person.setNic(personDTO.getNic());
    if (!(personDTO.getPrefix() == null || personDTO.getPrefix().getCode() != 0)) {
      person.setPrefix(Prefix.fromCode(personDTO.getMaritalStatus().getCode()));
    }
    person.setProfileImagePath(personDTO.getProfileImagePath());
    return person;
  }

  public static SecurityUserDTO convertDomainToDTO(SecurityUser securityUser) {
    if (!(securityUser == null)) {
      SecurityUserDTO securityUserDTO = new SecurityUserDTO();
      securityUserDTO.setAccountEnabled(securityUser.isAccountEnabled());
      securityUserDTO.setAccountExpired(securityUser.isAccountExpired());
      securityUserDTO.setAccountLocked(securityUser.isAccountLocked());
      securityUserDTO.setCreatedByUser(securityUser.getCreatedByUser());
      securityUserDTO.setCreationTime(securityUser.getCreationTime());
      securityUserDTO.setCredentialsExpired(securityUser.isCredentialsExpired());
      securityUserDTO.setDeleted(securityUser.isDeleted());



      securityUserDTO.setId(securityUser.getId());
      securityUserDTO.setPerson(getPersonDTO(securityUser.getPerson()));
      securityUserDTO.setModificationTime(securityUser.getModificationTime());
      securityUserDTO.setModifiedByUser(securityUser.getModifiedByUser());

      securityUserDTO.setSecret(securityUser.getSecret());
      securityUserDTO.setUserName(securityUser.getUserName());

      if (!(securityUser.getUserRoles() == null || securityUser.getUserRoles().isEmpty())) {
        Set<UserRoleDTO> userRoles = new HashSet<UserRoleDTO>();
        for (UserRole userRole : securityUser.getUserRoles()) {
          userRoles.add(UserRoleDTOConverter.convertDomainToDTO(userRole));
        }
        securityUserDTO.setUserRoles(userRoles);
      }

      securityUserDTO.setUuId(securityUser.getUuId());
      securityUserDTO.setVersionNumber(securityUser.getVersionNumber());
      return securityUserDTO;
    }
    return null;
  }

  private static PersonDTO getPersonDTO(Person person) {
    PersonDTO personDTO = new PersonDTO();
    if (!(person.getMaritalStatus() == null)) {
      personDTO.setMaritalStatus(
          new MaritalStatusDTO(person.getMaritalStatus().getCode(), person.getMaritalStatus().toString()));
    }
    personDTO.setProfileImagePath(person.getProfileImagePath());
    personDTO.setFullName(person.getFullName());
    if (!(person.getPrefix() == null)) {
      personDTO.setPrefix(new PrefixDTO(person.getPrefix().getCode(), person.getPrefix().getName()));
    }
    personDTO.setAddress(person.getAddress());
    personDTO.setDateOfBirth(person.getDateOfBirth());
    personDTO.setEmail(person.getEmail());
    personDTO.setId(person.getId());
    personDTO.setMobileNumber(person.getMobileNumber());
    personDTO.setLandNumber(person.getLandNumber());
    personDTO.setNic(person.getNic());
    return personDTO;
  }

  public static SecurityUser convertRequestToDomain(SecurityUserCreationRequest request,
      UserRoleRepository userRoleRepository) {
    SecurityUser securityUser = new SecurityUser();
    securityUser.setAccountEnabled(true);
    securityUser.setAccountExpired(false);
    securityUser.setAccountLocked(false);
    securityUser.setCredentialsExpired(false);
    securityUser.setDeleted(false);
    securityUser.setPerson(getPerson(request));
    securityUser.setSecret(new BCryptPasswordEncoder().encode(request.getSecret()));
    securityUser.setUserName(request.getUserName());
    securityUser.setUserRoles(getUserRoles(request.getUserRoles(), userRoleRepository));
    return securityUser;
  }


  private static Set<UserRole> getUserRoles(List<Long> userRoleIds, UserRoleRepository userRoleRepository) {
    if (!(userRoleIds == null || userRoleIds.isEmpty())) {
      Set<UserRole> userRoles = new HashSet<UserRole>();
      for (Long id : userRoleIds) {
        userRoles.add(userRoleRepository.findOne(id));
      }
      return userRoles;
    }
    return null;
  }

  private static Person getPerson(SecurityUserCreationRequest request) {
    Person person = new Person();
    person.setAddress(request.getAddress());
    person.setDeleted(false);
    person.setEmail(request.getEmail());
    person.setFullName(request.getFullName());
    person.setNic(request.getNic());
    person.setMobileNumber(request.getMobileNumber());
    person.setPrefix(Prefix.fromCode(request.getPrefixCode()));
    return person;
  }


  public static SecurityUser updateDomainFromRequest(SecurityUserUpdateRequest request,
      SecurityUser securityUserFromDB) {

    securityUserFromDB.setDeleted(request.isDeleted());

    securityUserFromDB.setPerson(getUpdatedPerson(securityUserFromDB.getPerson(), request));

    securityUserFromDB.setUserName(request.getUserName());

    return securityUserFromDB;
  }

  private static Person getUpdatedPerson(Person person, SecurityUserUpdateRequest request) {
    person.setAddress(request.getAddress());
    person.setDeleted(false);
    person.setEmail(request.getEmail());
    person.setFullName(request.getFullName());
    person.setNic(request.getNic());
    person.setMobileNumber(request.getMobileNumber());
    person.setPrefix(Prefix.fromCode(request.getPrefixCode()));
    return person;
  }

  public static SecurityUser updateDomainFromRequest(SecurityUserPermissionUpdateRequest request,
      SecurityUser securityUserFromDB, UserRoleRepository userRoleRepository) {
    securityUserFromDB.getUserRoles().clear();
    securityUserFromDB.setUserRoles(getUserRoles(request.getUserRoles(), userRoleRepository));
    return securityUserFromDB;
  }



  public static SecurityUser updateDomainFromRequest(SecurityUserProfileUpdateRequest request,
      SecurityUser securityUserFromDB) {
    securityUserFromDB.setPerson(getUpdatedPerson(securityUserFromDB.getPerson(), request));
    return securityUserFromDB;
  }

  private static Person getUpdatedPerson(Person person, SecurityUserProfileUpdateRequest request) {
    if (!(request.getPrefixName() == null)) {
      person.setPrefix(Prefix.fromCode(request.getPrefixCode()));
    }
    person.setFullName(request.getFullName());
    person.setDateOfBirth(request.getDateOfBirth());
    person.setNic(request.getNic());
    person.setMobileNumber(request.getMobileNumber());
    person.setLandNumber(request.getLandNumber());
    person.setEmail(request.getEmail());
    if (!(request.getMaritalStatusName() == null)) {
      person.setMaritalStatus(MaritalStatus.fromCode(request.getMaritalStatusCode()));
    }
    person.setAddress(request.getAddress());
    person.setProfileImagePath(request.getProfileImagePath());
    return person;
  }

}