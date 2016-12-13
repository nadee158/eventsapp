package com.janaka.projects.services.reports.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.janaka.projects.common.util.CommonUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class ReportFileUtil {
  private static String reportDbUrl = CommonUtil.getValueFromFile("reports", "database.access.url");
  static String templateLocation = CommonUtil.getValueFromFile("reports", "report.jrxml.template.location");
  static String customReportUploadLocation = CommonUtil.getValueFromFile("reports", "report.jrxml.template.location");

  private static Connection connection = null;

  public static Connection getReportDBConnection() {
    try {
      connection = (Connection) DriverManager.getConnection(reportDbUrl);
      return connection;
    } catch (SQLException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static JasperReport getJasperReport(String templateName) {
    try {
      return JasperCompileManager.compileReport(templateLocation + templateName);
    } catch (JRException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static JasperReport getCustomJasperReport(String templateName) {
    try {
      return JasperCompileManager.compileReport(customReportUploadLocation + templateName);
    } catch (JRException e) {
      e.printStackTrace();
      return null;
    }
  }
}
