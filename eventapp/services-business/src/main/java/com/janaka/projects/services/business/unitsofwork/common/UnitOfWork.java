package com.janaka.projects.services.business.unitsofwork.common;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.exceptions.BusinessException;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;

public abstract class UnitOfWork {


  private Logger logger = Logger.getLogger(this.getClass());
  private SecurityContext securityContext = null;
  private AuditContext auditContext = null;
  private List<String> operationalLogs = new ArrayList<String>();
  private Date startTimestamp = null;
  private Date endTimestamp = null;
  private boolean mustIncludeCustomMessagesInLog = true;

  private JmxNotificationPublisher jmxNotificationPublisher;

  public SecurityContext getSecurityContext() {
    return this.securityContext;
  }

  public void setSecurityContext(SecurityContext securityContext) {
    this.securityContext = securityContext;
  }

  public AuditContext getAuditContext() {
    return this.auditContext;
  }

  public void setAuditContext(AuditContext auditContext) {
    this.auditContext = auditContext;
  }

  public void execute() throws BusinessException {
    boolean isSuccessful = false;
    // log start time
    this.startTimestamp = Calendar.getInstance().getTime();

    try {
      try {

        jmxNotificationPublisher.notifyEventToJMX("Starting UoW [" + this.getClass().getSimpleName() + "]",
            "UOW Started");

      } catch (Exception ex) {
        ex.printStackTrace();
      }

      this.preExecute();

      this.doWork();

      isSuccessful = true;
    } catch (Exception ex) {
      // log error
      this.logger.error(ex.getMessage());

      BusinessException exception = new BusinessException(ex.getMessage(), ex);

      throw ex;
    } finally {
      this.postExecute(isSuccessful);
      this.endTimestamp = Calendar.getInstance().getTime();
      this.log();

      try {

        jmxNotificationPublisher.notifyEventToJMX("Ending UoW [" + this.getClass().getSimpleName() + "]", "UOW Ends");

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  protected void preExecute() {

  }

  protected abstract void doWork();

  protected void postExecute(boolean isSuccessful) {

  }

  protected final void log(String message) {
    this.operationalLogs.add(message);
  }

  private void log() {
    StringBuilder messageBuilder = new StringBuilder();

    try {
      // class name - unit of work
      // startTimestamp, endTimestamp, duration
      messageBuilder.append("Type:" + this.getClass().getName());
      messageBuilder.append("/r/n");

      messageBuilder.append("Start:" + this.startTimestamp);
      messageBuilder.append("/r/n");

      long duration = 0; // this.endTimestamp - this.startTimestamp;
      messageBuilder.append("Duration:" + duration);
      messageBuilder.append("/r/n");

      messageBuilder.append("End:" + this.endTimestamp);
      messageBuilder.append("/r/n");

      if (this.mustIncludeCustomMessagesInLog) {
        // operationalLogs
        messageBuilder.append("Operation Details:");
        messageBuilder.append("/r/n");

        for (String operationLog : this.operationalLogs) {
          messageBuilder.append(operationLog);
          messageBuilder.append("/r/n");
        }
      }

      String message = messageBuilder.toString();

      // write to log
      this.logger.debug(message);
      try {

        jmxNotificationPublisher.notifyEventToJMX("Details of UoW [" + this.getClass().getSimpleName() + "]", message);

      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  public UnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher) {
    this.jmxNotificationPublisher = giveJmxNotificationPublisher;
  }

}
