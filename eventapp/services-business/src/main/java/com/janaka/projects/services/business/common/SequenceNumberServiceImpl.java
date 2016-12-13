package com.janaka.projects.services.business.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.entitymanagement.dataaccessobjects.common.SequenceNumberRepository;
import com.janaka.projects.entitymanagement.domain.common.SequenceNumber;
import com.janaka.projects.entitymanagement.enums.SequenceNumberType;
import com.janaka.projects.services.common.SequenceNumberService;

@Service(value = "sequenceNumberService")
@Transactional()
public class SequenceNumberServiceImpl extends BusinessService implements SequenceNumberService {

  @Autowired
  private SequenceNumberRepository sequenceNumberRepository;

  @Override
  public String getNextReferenceNumberByNumberType(SequenceNumberType sequenceNumberType) {

    SequenceNumber nextSeqNumber = getNextSequenceNumberFromDB(sequenceNumberType.getCode());

    String paddedNo = StringUtils.leftPad(Long.toString(nextSeqNumber.getCurrentSequenceNumber()),
        nextSeqNumber.getNumberLength(), "0");

    String referenceNoPrefix = nextSeqNumber.getPrefixValue();

    if (StringUtils.isNotEmpty(referenceNoPrefix)) {
      if (referenceNoPrefix.contains(ApplicationConstants.YEAR)) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        referenceNoPrefix =
            referenceNoPrefix.replace(ApplicationConstants.YEAR, sdf.format(Calendar.getInstance().getTime()));
      }
      if (referenceNoPrefix.contains(ApplicationConstants.MONTH)) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM");
        referenceNoPrefix =
            referenceNoPrefix.replace(ApplicationConstants.MONTH, sdf.format(Calendar.getInstance().getTime()));
      }
      if (referenceNoPrefix.contains(ApplicationConstants.DATE)) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        referenceNoPrefix =
            referenceNoPrefix.replace(ApplicationConstants.DATE, sdf.format(Calendar.getInstance().getTime()));
      }
    }
    return referenceNoPrefix + paddedNo;
  }

  @org.springframework.transaction.annotation.Transactional(isolation = Isolation.SERIALIZABLE,
      propagation = Propagation.REQUIRES_NEW, readOnly = false)
  public synchronized SequenceNumber getNextSequenceNumberFromDB(long code) {
    SequenceNumber sequenceNumber = sequenceNumberRepository.findOne(code);
    sequenceNumber.setCurrentSequenceNumber(sequenceNumber.getCurrentSequenceNumber() + 1);
    return sequenceNumberRepository.save(sequenceNumber);
  }

  @org.springframework.transaction.annotation.Transactional(readOnly = false)
  @Override
  public void createSequenceNumber(SequenceNumber sequenceNumber) {
    sequenceNumberRepository.save(sequenceNumber);
  }



}
