package com.janaka.projects.entitymanagement.dataaccessobjects.common;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.janaka.projects.entitymanagement.domain.common.SMSNotification;
import com.janaka.projects.entitymanagement.enums.YesNoStatus;

public interface SMSNotificationRepository extends DataTablesRepository<SMSNotification, Long> {

  List<SMSNotification> findBySentStatusAndRetryCountLessThanEqual(YesNoStatus sentStatus, int retryCount);

}
