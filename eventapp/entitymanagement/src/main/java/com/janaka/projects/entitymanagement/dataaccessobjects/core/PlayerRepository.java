package com.janaka.projects.entitymanagement.dataaccessobjects.core;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.core.Player;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public interface PlayerRepository extends DataTablesRepository<Player, Long> {

  public List<Player> findByRecordStatus(RecordStatus active);

  public Player findByPerson_Nic(String nic);

  public Player findByPlayerNumber(String playerNumber);

}
