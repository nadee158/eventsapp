package com.janaka.projects.services.business.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.janaka.projects.dtos.requests.core.EventCreationRequest;
import com.janaka.projects.dtos.requests.core.mock.MockEventUtil;
import com.janaka.projects.dtos.responses.core.EventCreationResponse;
import com.janaka.projects.services.core.EventService;
import com.janaka.projects.services.test.Application;
import com.janaka.projects.services.test.RepositoryConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, RepositoryConfiguration.class})
public class TestEventService {

  @Autowired
  private EventService eventService;

  @Test
  public void testCreateEvent() {
    EventCreationRequest request = MockEventUtil.getEventCreationRequest();
    EventCreationResponse response = eventService.createEvent(request);
    Assert.assertNotNull(response);
    System.out.println(response.getMessage());
    Assert.assertNotNull(response.getEventDTO());
    Assert.assertNotEquals(response.getId(), 0);
  }

}
