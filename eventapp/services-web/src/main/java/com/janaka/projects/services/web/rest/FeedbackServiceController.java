package com.janaka.projects.services.web.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.services.common.SecurityService;

@RestController
@RequestMapping(ServiceEndpoints.FEEDBACK_NAMESAPCE)
public class FeedbackServiceController {


  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private SecurityService service = null;


  @RequestMapping(ServiceEndpoints.SUBMIT_FEEDBACK)
  public void signOut() {
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();

  }

}
