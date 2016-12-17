package com.janaka.projects.services.business.domaindtoconverter.core;

import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.requests.core.PlayerCreationRequest;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.domain.usermanagement.Person;

public class PlayerDTOConverter {

  public static Player convertRequestToDomain(PlayerCreationRequest request, CategorySetup categorySetup,
      Person person) {
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
      player.setTeam(request.getTeam());
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
      dto.setEmail(player.getPerson().getEmail());
      dto.setFullName(player.getPerson().getFullName());
      dto.setHeight(player.getHeight());
      dto.setId(player.getId());
      dto.setNic(player.getPerson().getNic());
      dto.setPersonId(player.getPerson().getId());
      dto.setPlayerNumber(player.getPlayerNumber());
      dto.setProfileImagePath(player.getPerson().getProfileImagePath());
      dto.setTeam(player.getTeam());
      dto.setWeight(player.getWeight());
      dto.setVersion(player.getVersion());
      dto.setRecordStatus(player.getRecordStatus().toString());
      return dto;
    }
    return null;
  }


}
