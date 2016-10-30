package com.janaka.projects.services.business.unitsofwork.documentmanagement;

import java.util.UUID;

import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaRepository;
import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.documentmanagement.MediaDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class DocumentRetrievalUnitOfWork extends UnitOfWork {

  private MediaRepository mediaRepository = null;

  private long referenceKey = 0;

  private DocumentResponse response = null;

  private SecurityService securityService = null;

  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private Media media = null;

  @Override
  protected void preExecute() {
    String downloadParam = UUID.randomUUID() + "_" + referenceKey;

    this.media = mediaRepository.findOne(this.referenceKey);
    this.media.setDowloadParam(downloadParam);
    mediaRepository.save(this.media);
  }

  @Override
  protected void doWork() {
    if (this.referenceKey != 0) {
      this.media = mediaRepository.findOne(this.referenceKey);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    if (!(this.media == null)) {
      this.response = MediaDTOConverter.convertDomainToResponse(this.media);
      String downloadURL =
          CommonUtil.getValueFromFile("application", "rdd.document.download.url") + this.media.getDowloadParam();
      this.response.getDocumentDTO().setDownloadURL(downloadURL);
    }
    super.postExecute(isSuccessful);
  }

  public DocumentRetrievalUnitOfWork(MediaRepository mediaRepository, long referenceKey,
      SecurityService securityService, SecurityContext securityContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.mediaRepository = mediaRepository;
    this.referenceKey = referenceKey;
    this.securityService = securityService;
    this.securityContext = securityContext;
  }

  public DocumentResponse getResponse() {
    return response;
  }

  public void setResponse(DocumentResponse response) {
    this.response = response;
  }

}
