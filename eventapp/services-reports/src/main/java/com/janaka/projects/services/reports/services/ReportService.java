package com.janaka.projects.services.reports.services;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.services.reports.domains.ReportTemplate;
import com.janaka.projects.services.reports.dto.CreateReportRequest;
import com.janaka.projects.services.reports.dto.CreateReportResponse;
import com.janaka.projects.services.reports.dto.ReportTemplateResponse;

public interface ReportService {

  public CreateReportResponse createReport(SecurityContext securityContext, AuditContext auditContext,
      CreateReportRequest request);

  public ReportTemplate getReportReportTemplateById(long reportId) throws Exception;

  public List<ReportTemplate> getAllReportTemplates() throws Exception;

  public List<ReportTemplateResponse> getAllReportTemplateResponses();

  public void saveReportTemplate(ReportTemplate reportTemplate) throws Exception;

  public void updateReportTemplate(ReportTemplate reportTemplate) throws Exception;

  public CreateReportResponse createCustomReports(SecurityContext securityContext, AuditContext auditContext,
      String fileName, Map<String, Object> params, HttpServletResponse response);

  public String printCustomReports(String fileName, Map<String, Object> parameters, HttpServletResponse response);

}
