package com.janaka.projects.services.business.unitsofwork.documentmanagement;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaDownloadLogRepository;
import com.janaka.projects.entitymanagement.dataaccessobjects.documentmanagement.MediaRepository;
import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;
import com.janaka.projects.entitymanagement.domain.documentmanagement.MediaDownloadLog;
import com.janaka.projects.services.business.common.JmxNotificationPublisher;
import com.janaka.projects.services.business.unitsofwork.common.UnitOfWork;
import com.janaka.projects.services.common.SecurityService;

public class DocumentDownloadUnitOfWork extends UnitOfWork {

  private MediaRepository mediaRepository = null;
  private MediaDownloadLogRepository mediaDownloadLogRepository = null;

  private String downloadParam = null;
  private String fileName = null;

  private HttpServletResponse response = null;

  private SecurityService securityService = null;

  private SecurityContext securityContext = null;

  private User userFromSession = null;

  private Media media = null;

  private boolean successStatus = false;

  @Override
  protected void preExecute() {
    try {
      long key = Long.parseLong(downloadParam.trim().split("_")[1]);

      this.media = mediaRepository.findOne(key);
      if (this.media != null && StringUtils.equals(this.media.getDowloadParam(), this.downloadParam)) {
        this.fileName = this.media.getGeneratedFileName();
      } else {
        this.pushErrorResponse("4005", "download url is expired");
      }

    } catch (Exception e) {
      try {
        this.pushErrorResponse("4003", "invalid parameter");
      } catch (Exception s) {
        s.printStackTrace();
      }
    }
  }

  @Override
  protected void doWork() {
    try {
      if (this.fileName != null) {
        String filePath = CommonUtil.getValueFromFile("application", "rdd.document.manager.upload.location");
        String fileURL = filePath + "/" + fileName;
        File file = new File(fileURL);

        if (!file.exists()) {
          this.pushErrorResponse("4004", "file is not found");
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
          System.out.println("mimetype is not detectable, will take default");
          mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        /*
         * "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything
         * viewable by browser] right on browser while others(zip e.g) will be directly downloaded
         * [may provide save as popup, based on your browser setting.]
         */
        response.setHeader("Content-Disposition",
            String.format("inline; filename=\"" + this.media.getOriginalFileName() + "\""));

        /*
         * "Content-Disposition : attachment" will be directly download, may provide save as popup,
         * based on your browser setting
         */

        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        // Copy bytes from source to destination(outputstream in this
        // example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
        this.successStatus = true;
      } else {
        this.pushErrorResponse("4008", "invalid parameter; file name cannot be generated");
      }
    } catch (Exception e) {
      throw new RuntimeException("IOError writing file to output stream");
    }

  }

  @Override
  protected void postExecute(boolean isSuccessful) {
    if (isSuccessful) {
      this.media.setDowloadParam(null);
      mediaRepository.save(this.media);
      MediaDownloadLog downloadLog = new MediaDownloadLog();
      downloadLog.setMediaId(this.media.getId());
      downloadLog.setIsSuccess(successStatus);
      mediaDownloadLogRepository.save(downloadLog);
    }
    super.postExecute(isSuccessful);
  }

  public DocumentDownloadUnitOfWork(MediaRepository mediaRepository,
      MediaDownloadLogRepository mediaDownloadLogRepository, String downloadParam, HttpServletResponse response,
      SecurityService securityService, SecurityContext securityContext,
      JmxNotificationPublisher jmxNotificationPublisher) {
    super(jmxNotificationPublisher);
    this.mediaRepository = mediaRepository;
    this.mediaDownloadLogRepository = mediaDownloadLogRepository;
    this.downloadParam = downloadParam;
    this.response = response;
    this.securityService = securityService;
    this.securityContext = securityContext;
  }

  private void pushErrorResponse(String errorCode, String errorMessage) {
    try {
      String pushError = "{\"ErrorCode\":\"" + errorCode + "\", \"Message\":\"" + errorMessage + "\"}";
      System.out.println(pushError);
      OutputStream outputStream = this.response.getOutputStream();
      outputStream.write(pushError.getBytes(Charset.forName("UTF-8")));
      outputStream.close();
      return;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
