package com.janaka.projects.services.business.documentmanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.documentmanagement.DocumentRequest;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaAuditRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaDownloadLogRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaFormatRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaVersionRepository;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.documentmanagement.DocumentCreationUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.documentmanagement.DocumentDownloadUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.documentmanagement.DocumentModificationUnitOfWork;
import com.janaka.projects.services.business.unitsofwork.documentmanagement.DocumentRetrievalUnitOfWork;
import com.janaka.projects.services.common.SecurityService;
import com.janaka.projects.services.documentmanagement.DocumentMangementService;

@Service
@Transactional()
public class DocumentModuleServiceImpl extends BusinessService implements DocumentMangementService {

  @Autowired
  MediaRepository mediaRepository;

  @Autowired
  MediaFormatRepository mediaFormatRepository;

  @Autowired
  MediaDownloadLogRepository mediaDownloadLogRepository;

  @Autowired
  MediaVersionRepository mediaVersionRepository;

  @Autowired
  MediaAuditRepository mediaAuditRepository;

  @Autowired
  SecurityService securityService;

  @Autowired
  private JmxNotificationPublisher jmxNotificationPublisher;

  @Override
  public DocumentResponse createDocument(DocumentRequest request, AuditContext auditContext,
      SecurityContext securityContext) {

    DocumentCreationUnitOfWork creationUnitOfWork = new DocumentCreationUnitOfWork(mediaRepository,
        mediaFormatRepository, securityService, request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(creationUnitOfWork);
    return creationUnitOfWork.getResponse();
  }

  @Override
  public DocumentResponse updateDocument(DocumentRequest request, AuditContext auditContext,
      SecurityContext securityContext) {
    DocumentModificationUnitOfWork documentModificationUnitOfWork =
        new DocumentModificationUnitOfWork(mediaRepository, mediaFormatRepository, mediaVersionRepository,
            mediaAuditRepository, securityService, request, auditContext, securityContext, jmxNotificationPublisher);
    this.doWork(documentModificationUnitOfWork);
    return documentModificationUnitOfWork.getResponse();
  }

  @Override
  public DocumentResponse retrieveDocument(long documentReferenceKey, SecurityContext securityContext) {
    DocumentRetrievalUnitOfWork documentRetrievalUnitOfWork = new DocumentRetrievalUnitOfWork(mediaRepository,
        documentReferenceKey, securityService, securityContext, jmxNotificationPublisher);
    this.doWork(documentRetrievalUnitOfWork);
    return documentRetrievalUnitOfWork.getResponse();
  }

  @Override
  public void downloadDocument(String donloadParam, HttpServletRequest request, HttpServletResponse response,
      SecurityContext securityContext) {
    DocumentDownloadUnitOfWork documentDownloadUnitOfWork = new DocumentDownloadUnitOfWork(mediaRepository,
        mediaDownloadLogRepository, donloadParam, response, securityService, securityContext, jmxNotificationPublisher);
    this.doWork(documentDownloadUnitOfWork);
  }

}
