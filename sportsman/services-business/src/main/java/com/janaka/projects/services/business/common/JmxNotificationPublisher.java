package com.janaka.projects.services.business.common;

import javax.management.Notification;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.stereotype.Component;

@ManagedResource(objectName = "mbeans:name=EVENTSJmxNotificationPublisherBean",
    description = "JmxNotificationPublisher EVENTS.")
@Component("jmxNotificationPublisher")
public class JmxNotificationPublisher implements NotificationPublisherAware {

  private NotificationPublisher notificationPublisher;

  private static int sequenceNumber = 0;

  public void notifyEventToJMX(String type, String msg) {
    try {
      if (!(notificationPublisher == null)) {
        sequenceNumber = sequenceNumber + 1;
        notificationPublisher.sendNotification(new Notification(type, this, sequenceNumber, msg));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
    this.notificationPublisher = notificationPublisher;
  }

}
