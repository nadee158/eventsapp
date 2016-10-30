package com.janaka.projects.services.reports.services.business;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Service;

import com.janaka.projects.common.caching.Cache;
import com.janaka.projects.common.caching.CacheFactory;
import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.Session;
import com.janaka.projects.common.security.User;
import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.services.reports.dao.ReportTemplateRepository;
import com.janaka.projects.services.reports.domains.ReportTemplate;
import com.janaka.projects.services.reports.dto.CreateReportRequest;
import com.janaka.projects.services.reports.dto.CreateReportResponse;
import com.janaka.projects.services.reports.dto.ReportTemplateResponse;
import com.janaka.projects.services.reports.services.ReportService;
import com.janaka.projects.services.reports.util.ReportFileExportUtil;
import com.janaka.projects.services.reports.util.ReportFileUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Service(value = "reportService")
public class ReportServiceImpl implements ReportService {
  private Logger logger = Logger.getLogger(this.getClass());

  private static final String PROP_FILE_NAME = "reports";

  private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

  protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

  private static Connection connection = null;

  @Autowired
  private ReportTemplateRepository reportTemplateRepository;

  @Override
  public CreateReportResponse createReport(SecurityContext securityContext, AuditContext auditContext,
      CreateReportRequest request) {
    CreateReportResponse response = new CreateReportResponse();
    boolean isValidSession = true;
    int statusCode = ApplicationConstants.STATUS_CODE_OK;
    // try {
    // isValidSession = ensureSessionValidity(securityContext, auditContext);
    // } catch (Exception e) {
    // e.printStackTrace();
    // statusCode = ApplicationConstants.UNAUTHORIZED;
    // logger.error(e);
    // }

    if (isValidSession) {
      try {
        response = generateReport(request, response);
      } catch (Exception e) {
        e.printStackTrace();
        statusCode = ApplicationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR;
        logger.error(e);
      }
    } else {
      statusCode = ApplicationConstants.STATUS_CODE_UNAUTHORIZED;
    }
    response.setStatusCode(statusCode);
    logger.debug(response);
    return response;
  }


  private boolean ensureSessionValidity(SecurityContext securityContext, AuditContext auditContext) {
    // TODO: check in cache and return session if 1) existent 2) non-expired 3) user is still valid
    boolean isValid = false;
    Cache cache = CacheFactory.getCache();

    // if 1) existent
    Session session = cache.get(securityContext.getToken());

    if (!(session == null)) {
      // 2) non-expired
      Date lastAccessedTime = session.getLastRequestTimestamp();
      Date now = Calendar.getInstance().getTime();
      long diff = now.getTime() - lastAccessedTime.getTime();
      System.out.println("diff :" + diff);
      long expiryPeriod = session.getExpires();
      System.out.println("expiryPeriod :" + expiryPeriod);
      if (!(diff > expiryPeriod)) {
        User userFromCache = cache.get(session.getName());
        detailsChecker.check(userFromCache);
        isValid = true;
      } else {
        // user expired
        cache.remove(securityContext.getToken());
        session = null;
        throw new AccountExpiredException(
            messages.getMessage("AccountStatusUserDetailsChecker.expired", "User account has expired"));
      }
    } else {
      // invalid token
      throw new BadCredentialsException("Invalid Token");
    }

    session.setLastRequestTimestamp(Calendar.getInstance().getTime());
    cache.set(securityContext.getToken(), session);
    return isValid;
  }


  private CreateReportResponse generateReport(CreateReportRequest request, CreateReportResponse response)
      throws Exception {
    Date reportDate = Calendar.getInstance().getTime();
    JRDataSource reportData = null;
    if (!(request.getList() == null || request.getList().isEmpty())) {
      // 1. Add report parameters
      reportData = new JRBeanCollectionDataSource(request.getList());
    } else {
      reportData = new JRBeanCollectionDataSource(new ArrayList<>());
    }

    // 1. Add report parameters
    HashMap<String, Object> params = request.getParams();
    if (params == null) {
      params = new HashMap<String, Object>();
    }

    String reportJrxmlFilePath = CommonUtil.getValueFromFile(PROP_FILE_NAME, "report.jrxml.template.location");

    ReportTemplate reportTemplate = reportTemplateRepository.findOne(request.getReportId());
    if (!(reportTemplate == null)) {

      InputStream is = null;
      try {
        is = new FileInputStream(reportJrxmlFilePath + reportTemplate.getUploadedFileName());
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }

      if (!(request.getSubreportList() == null || request.getSubreportList().isEmpty())) {
        for (Entry<Long, String> entry : request.getSubreportList().entrySet()) {
          ReportTemplate subReportTemplate = reportTemplateRepository.findOne(entry.getKey());
          InputStream sis = null;
          try {
            sis = new FileInputStream(reportJrxmlFilePath + subReportTemplate.getReportName());
            JasperDesign jdSubReport = JRXmlLoader.load(sis);
            JasperReport jasperSubReport = JasperCompileManager.compileReport(jdSubReport);
            params.put(entry.getValue(), jasperSubReport);
            sis.close();
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      System.out.println("params " + params);
      // 3. Convert template to JasperDesign
      JasperDesign jd = JRXmlLoader.load(is);
      // 4. Compile design to JasperReport
      JasperReport jr = JasperCompileManager.compileReport(jd);
      // 5. Create the JasperPrint object
      // Make sure to pass the JasperReport, report parameters, and data source
      JasperPrint jp = JasperFillManager.fillReport(jr, params, reportData);
      // 6. Create an output byte stream where data will be written
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      String fileName = request.getGeneratedReportFileName();
      if (StringUtils.isEmpty(fileName)) {
        fileName = "tempreportfile_" + System.currentTimeMillis();
      }

      fileName = ReportFileExportUtil.getInstance().export(request.getFileType(), jp, baos, fileName);

      response.setFileName(fileName);
      response.setReportDate(reportDate);

      try {
        is.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }

    return response;
  }


  @Override
  public ReportTemplate getReportReportTemplateById(long reportId) throws Exception {
    return reportTemplateRepository.findOne(reportId);
  }

  @Override
  public List<ReportTemplate> getAllReportTemplates() throws Exception {
    return (List<ReportTemplate>) reportTemplateRepository.findAll();
  }

  @Override
  public List<ReportTemplateResponse> getAllReportTemplateResponses() {
    List<ReportTemplate> templateList = (List<ReportTemplate>) reportTemplateRepository.findAll();
    List<ReportTemplateResponse> templateResponses = new ArrayList<ReportTemplateResponse>();
    for (ReportTemplate template : templateList) {
      ReportTemplateResponse reportTemplateResponse = new ReportTemplateResponse();
      reportTemplateResponse.setOriginalFileName(template.getOriginalFileName());
      reportTemplateResponse.setReportDescription(template.getReportDescription());
      reportTemplateResponse.setReportName(template.getReportName());
      reportTemplateResponse.setReportType(template.getReportType());
      reportTemplateResponse.setUploadedFileName(template.getUploadedFileName());
      reportTemplateResponse.setVersion(template.getVersion());

      templateResponses.add(reportTemplateResponse);
    }
    return templateResponses;
  }

  @Override
  public void saveReportTemplate(ReportTemplate reportTemplate) throws Exception {
    reportTemplateRepository.save(reportTemplate);
  }

  @Override
  public void updateReportTemplate(ReportTemplate reportTemplate) throws Exception {
    ReportTemplate reportTemplateFromDB = reportTemplateRepository.findOne(reportTemplate.getId());
    reportTemplateFromDB.updateFromReportTemplateFromUI(reportTemplate);
    reportTemplateRepository.save(reportTemplateFromDB);
  }

  @Override
  public CreateReportResponse createCustomReports(SecurityContext securityContext, AuditContext auditContext,
      String fileName, Map<String, Object> params, HttpServletResponse response) {

    CreateReportResponse reportResponse = new CreateReportResponse();
    boolean isValidSession = true;
    int statusCode = ApplicationConstants.STATUS_CODE_OK;
    JasperReport jasperReport;
    JasperPrint jasperPrint;

    if (isValidSession) {
      try {
        jasperReport = ReportFileUtil.getCustomJasperReport(fileName);
        connection = ReportFileUtil.getReportDBConnection();

        jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
      } catch (Exception e) {
        e.printStackTrace();
        statusCode = ApplicationConstants.STATUS_CODE_INTERNAL_SERVER_ERROR;
        logger.error(e);
      }
    } else {
      statusCode = ApplicationConstants.STATUS_CODE_UNAUTHORIZED;
    }
    reportResponse.setStatusCode(statusCode);
    logger.debug(response);

    return reportResponse;
  }


  @Override
  public String printCustomReports(String fileName, Map<String, Object> parameters, HttpServletResponse response) {
    JasperReport jasperReport;
    JasperPrint jasperPrint;

    try {

      jasperReport = ReportFileUtil.getCustomJasperReport(fileName);
      connection = ReportFileUtil.getReportDBConnection();
      String fileLocation = "D:/sharedstorage/Report_Files/";
      String createdFileName = this.createRandomName() + ".pdf";
      String createdFullPath = fileLocation + createdFileName;

      jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
      JasperExportManager.exportReportToPdfFile(jasperPrint, createdFullPath);

      File file = new File(createdFullPath);
      long prevLength = 0;
      long newLength = file.length();

      while (prevLength < newLength) {
        prevLength = newLength;
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        newLength = file.length();
        System.out.println("Previous size: " + prevLength);// print out the size for testing
        System.out.println("New size: " + newLength);// print out the
      }

      return createdFileName;

    } catch (JRException e) {
      e.printStackTrace();
      return null;
    }
  }

  private String createRandomName() {
    char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      char c = chars[random.nextInt(chars.length)];
      sb.append(c);
    }
    String output = sb.toString();
    System.out.println(output);
    return output;
  }


}
