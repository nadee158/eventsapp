package com.janaka.projects.services.business.common;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.entitymanagement.domain.common.SequenceNumber;
import com.janaka.projects.entitymanagement.enums.SequenceNumberType;
import com.janaka.projects.services.common.SequenceNumberService;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, RepositoryConfiguration.class})
public class TestSequenceNumberService {

  @Autowired
  private SequenceNumberService sequenceNumberService;

  // @Test
  public void testCreateSequenceNumber() {
    SequenceNumber sequenceNumber = new SequenceNumber();
    sequenceNumber.setCurrentSequenceNumber(0);
    sequenceNumber.setDescription("Player Number Sequence");
    sequenceNumber.setNumberLength(5);
    sequenceNumber.setPrefixValue("PL/YEAR/");
    sequenceNumberService.createSequenceNumber(sequenceNumber);
  }

  // @Test
  public void testGetNextReferenceNumberByNumberType() {
    String number = sequenceNumberService.getNextReferenceNumberByNumberType(SequenceNumberType.PLAYER_NUMBER);
    System.out.println("number :" + number);
    Assert.assertNotNull(number);
  }



}
