package com.janaka.projects.services.common;

import com.janaka.projects.entitymanagement.domain.common.SequenceNumber;
import com.janaka.projects.entitymanagement.enums.SequenceNumberType;

public interface SequenceNumberService {

  public String getNextReferenceNumberByNumberType(SequenceNumberType sequenceNumberType);

  public void createSequenceNumber(SequenceNumber sequenceNumber);



}
