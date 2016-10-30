package com.janaka.projects.services.business.unitsofwork.documentmanagement;

import java.util.ArrayList;
import java.util.List;

import com.janaka.projects.common.documentmanagement.FileMetaData;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.common.util.FileUtil;
import com.janaka.projects.dtos.requests.common.GetSessionDetailsRequest;
import com.janaka.projects.dtos.requests.documentmanagement.DocumentRequest;
import com.janaka.projects.dtos.responses.common.GetSessionDetailsResponse;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaFormatRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaRepository;
import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;
import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaFormat;
import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaVersion;
import com.janaka.projects.entitymanagement.enums.MediaType;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.domaindtoconverter.documentmanagement.MediaDTOConverter;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class DocumentCreationUnitOfWork extends UnitOfWork {

  private MediaRepository mediaRepository = null;
  private MediaFormatRepository mediaFormatRepository = null;

  private SecurityService securityService = null;

  private DocumentRequest request = null;
  private DocumentResponse response = null;

  private AuditContext auditContext = null;
  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private Media media = null;

  @Override
  protected void preExecute() {
    if (!(securityContext == null || securityContext.getToken() == null)) {
      GetSessionDetailsRequest getSessionDetailsRequest = new GetSessionDetailsRequest();
      getSessionDetailsRequest.setToken(securityContext.getToken());
      GetSessionDetailsResponse getSessionDetailsResponse = securityService.getSessionDetails(getSessionDetailsRequest);
      if (!(getSessionDetailsResponse == null)) {
        userFromSession = getSessionDetailsResponse.getUser();
      }
      super.preExecute();
    }

    if (!(request == null)) {
      this.media = MediaDTOConverter.convertRequestToDomain(this.request);
      if (!(media == null)) {
        String createdUser = null;
        if (!(userFromSession == null)) {
          createdUser = userFromSession.getSecurityUserId().toString();
        }

        FileMetaData fileMetaData = FileUtil.getFileMetaData(this.request.getDocumentDTO().getFile(),
            CommonUtil.getValueFromFile("application", "rdd.document.manager.upload.location"));
        if (!(fileMetaData == null)) {
          MediaFormat mediaFormat = null;
          mediaFormat = mediaFormatRepository.findByMimeType(fileMetaData.getMimeType());
          if (mediaFormat == null) {
            MediaFormat newMediaFormat = new MediaFormat();
            newMediaFormat.setMimeType(fileMetaData.getMimeType());
            newMediaFormat.setFileExtension(fileMetaData.getExtension());
            newMediaFormat.setFileSize(fileMetaData.getFileSize());
            int mediaTypeCode = FileUtil.getMediaTypeByMimeType(fileMetaData.getMimeType());
            MediaType mediaType = MediaType.fromCode(mediaTypeCode);
            newMediaFormat.setMediaType(mediaType);
            newMediaFormat.setCreatedByUser(createdUser);

            mediaFormat = mediaFormatRepository.save(newMediaFormat);
          }

          MediaVersion mediaVersion = new MediaVersion();
          mediaVersion.setCreatedByUser(createdUser);
          mediaVersion.setMediaVersion(1);
          mediaVersion.setMedia(this.media);
          List<MediaVersion> mediaVersionList = new ArrayList<MediaVersion>();
          mediaVersionList.add(mediaVersion);

          this.media.setActive(true);
          this.media.setOriginalFileName(fileMetaData.getOriginalFileName());
          this.media.setGeneratedFileName(fileMetaData.getGeneratedFileName());
          this.media.setFilePath(fileMetaData.getFilePath());
          this.media.setMediaFormat(mediaFormat);
          this.media.setMediaVersions(mediaVersionList);
        }
      }
    }
  }

  @Override
  protected void doWork() {
    if (!(this.media == null)) {
      this.media = mediaRepository.save(this.media);
    }
  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    if (isSuccessful) {
      this.response = MediaDTOConverter.convertDomainToResponse(this.media);
    }
    super.postExecute(isSuccessful);
  }

  public DocumentCreationUnitOfWork(MediaRepository mediaRepository, MediaFormatRepository mediaFormatRepository,
      SecurityService securityService, DocumentRequest request, AuditContext auditContext,
      SecurityContext securityContext, JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.mediaRepository = mediaRepository;
    this.mediaFormatRepository = mediaFormatRepository;
    this.securityService = securityService;
    this.request = request;
    this.auditContext = auditContext;
    this.securityContext = securityContext;
  }

  public DocumentResponse getResponse() {
    return response;
  }

  public void setResponse(DocumentResponse response) {
    this.response = response;
  }

}
