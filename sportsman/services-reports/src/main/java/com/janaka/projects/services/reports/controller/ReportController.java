package com.janaka.projects.services.reports.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.services.reports.dto.CreateReportRequest;
import com.janaka.projects.services.reports.dto.CreateReportResponse;
import com.janaka.projects.services.reports.dto.ReportParamDTO;
import com.janaka.projects.services.reports.dto.ReportTemplateResponse;
import com.janaka.projects.services.reports.services.ReportService;
import com.janaka.projects.services.reports.util.ReportFileUtil;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping(ServiceEndpoints.REPORTS)
public class ReportController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private ReportService reportService;


  @RequestMapping(value = ServiceEndpoints.CREATE_REPORT, method = RequestMethod.POST)
  public CreateReportResponse createReport(@RequestBody CreateReportRequest request) {
    logger.debug(request);
    AuditContext auditContext = SecurityHelper.getAuditContext();
    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    return reportService.createReport(securityContext, auditContext, request);
  }

  @RequestMapping(value = ServiceEndpoints.REPORT_LIST, method = RequestMethod.GET)
  public List<ReportTemplateResponse> loadReportList() {
    return reportService.getAllReportTemplateResponses();
  }

  @RequestMapping(value = ServiceEndpoints.REPORT_PARAMS, method = RequestMethod.GET)
  public ArrayList<ReportParamDTO> loadReportParams(@RequestParam("jasperName") String fileName) {
    List<ReportParamDTO> paramList = new ArrayList<ReportParamDTO>();

    JasperReport jasperReport = ReportFileUtil.getCustomJasperReport(fileName);
    JRParameter[] params = jasperReport.getParameters();

    for (JRParameter param : params) {
      if (!param.isSystemDefined() && param.isForPrompting()) {
        System.out.println("---" + param.getName());
        ReportParamDTO paramDTO = new ReportParamDTO();
        paramDTO.setName(param.getName());
        paramDTO.setType(param.getValueClassName());
        paramList.add(paramDTO);
        System.out.println(">>>>>>>>>>>>>>>" + param.getDescription());
        System.out.println(">>>>>>>>>>>>>>>" + param.getDefaultValueExpression());
        System.out.println(">>>>>>>>>>>>>>>" + param.getNestedTypeName());
        System.out.println(">>>>>>>>>>>>>>>" + param.getValueClassName());
        System.out.println(">>>>>>>>>>>>>>>" + param.getValueClass());
      }
    }

    return (ArrayList<ReportParamDTO>) paramList;
  }

  @RequestMapping(value = ServiceEndpoints.CREAT_CUSTOM_REPORT, method = RequestMethod.GET)
  public Map<String, String> getReportParamPost(@RequestParam("fileName") String fileName,
      @RequestParam("reportParams") ArrayList<String> reportParams, HttpServletResponse response) {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> parameters = new HashMap<String, Object>();

    try {
      for (String arrayList : reportParams) {
        ReportParamDTO reportParam = mapper.readValue(arrayList, ReportParamDTO.class);
        String type = reportParam.getType().split("\\.")[2];

        if (StringUtils.equals(type, "String")) {
          parameters.put(reportParam.getName(), reportParam.getValue());
        } else if (StringUtils.equals(type, "Integer")) {
          parameters.put(reportParam.getName(), Integer.parseInt(reportParam.getValue()));
        } else if (StringUtils.equals(type, "Time")) {
          parameters.put(reportParam.getName(), reportParam.getValue());
        } else {
          parameters.put(reportParam.getName(), reportParam.getValue());
        }
      }

      String reportName = reportService.printCustomReports(fileName, parameters, response);
      Map<String, String> map = new HashMap<String, String>();
      map.put("fileName", reportName);
      return map;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value = ServiceEndpoints.DOWN_FILE, method = RequestMethod.GET)
  public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) {
    System.out.println("====" + fileName);
    response.setContentType("application/pdf");
    response.setHeader("content-disposition", "filename=" + fileName + ".pdf");
    try {
      InputStream is = new FileInputStream("D:/sharedstorage/Report_Files/" + fileName);
      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
      response.flushBuffer();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
