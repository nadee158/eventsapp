package com.janaka.projects.services.business.unitsofwork.documentmanagement;

import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;

public class DocumentDeletionUnitOfWork extends UnitOfWork {

  public DocumentDeletionUnitOfWork(JmxNotificationPublisher giveJmxNotificationPublisher) {
    super(giveJmxNotificationPublisher);
  }

  @Override
  protected void doWork() {
    // TODO Auto-generated method stub

  }

}
