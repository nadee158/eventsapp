package com.janaka.projects.services.business.domaindtoconverter.core;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.requests.core.PlayerCreationRequest;
import com.janaka.projects.dtos.requests.core.PlayerUpdateRequest;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.domain.core.Team;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class PlayerDTOConverter {

  public static Player convertRequestToDomain(PlayerCreationRequest request, CategorySetup categorySetup, Person person,
      Team team) {
    if (!(request == null)) {
      Player player = new Player();
      player.setCategorySetup(categorySetup);
      player.setHeight(request.getHeight());
      if (!(person == null)) {
        player.setPerson(person);
      } else {
        player.setPerson(constructPerson(request));
      }
      player.setPlayerNumber(request.getPlayerNumber());
      player.setTeam(team);
      player.setWeight(request.getWeight());
      return player;
    }
    return null;
  }

  private static Person constructPerson(PlayerCreationRequest request) {
    Person person = new Person();
    person.setAddress(request.getAddress());
    person.setContactNumber(request.getContactNumber());
    person.setDateOfBirth(CommonUtil.getParsedDate(request.getDateOfBirth()));
    person.setEmail(request.getEmail());
    person.setFullName(request.getPlayerName());
    person.setNic(request.getIcPassport());
    person.setProfileImagePath(request.getSavedImagePath());
    return person;
  }

  public static PlayerDTO convertDomainToDTO(Player player) {
    if (!(player == null)) {
      PlayerDTO dto = new PlayerDTO();

      dto.setAddress(player.getPerson().getAddress());
      dto.setCategoryId(player.getCategorySetup().getId());
      dto.setContactNumber(player.getPerson().getContactNumber());
      dto.setCategorySetupName(player.getCategorySetup().getCategorySetupName());
      dto.setEmail(player.getPerson().getEmail());
      dto.setFullName(player.getPerson().getFullName());
      dto.setHeight(player.getHeight());
      dto.setId(player.getId());
      dto.setNic(player.getPerson().getNic());
      dto.setPersonId(player.getPerson().getId());
      dto.setPlayerNumber(player.getPlayerNumber());
      dto.setProfileImagePath(player.getPerson().getProfileImagePath());
      dto.setTeamId(player.getTeam().getId());
      dto.setTeamName(player.getTeam().getTeamName());
      dto.setWeight(player.getWeight());
      dto.setVersion(player.getVersion());
      dto.setRecordStatus(player.getRecordStatus().getRecordStatusCode());
      dto.setEventName(player.getCategorySetup().getEvent().getEventName());
      dto.setEventId(player.getCategorySetup().getEvent().getId());
      return dto;
    }
    return null;
  }

  public static Player updateDomainFromRequest(PlayerUpdateRequest request, CategorySetup categorySetup, Player player,
      Person person, Team team) {
    if (!(request == null)) {
      player.setCategorySetup(categorySetup);
      player.setHeight(request.getHeight());
      player.setPerson(constructPerson(person, request));
      player.setPlayerNumber(request.getPlayerNumber());
      player.setTeam(team);
      player.setWeight(request.getWeight());
      if (StringUtils.isNotEmpty(request.getRecordStatus())) {
        player.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
      }
      return player;
    }
    return null;
  }


  private static Person constructPerson(Person person, PlayerUpdateRequest request) {
    if (person == null) {
      person = new Person();
    }
    person.setAddress(request.getAddress());
    person.setContactNumber(request.getContactNumber());
    person.setEmail(request.getEmail());
    person.setFullName(request.getFullName());
    person.setNic(request.getNic());
    System.out.println("request.getSavedImagePath() " + request.getSavedImagePath());
    if (StringUtils.isNotEmpty(request.getSavedImagePath())) {
      person.setProfileImagePath(request.getSavedImagePath());
    } else {
      person.setProfileImagePath(request.getProfileImagePath());
    }
    return person;
  }


}
