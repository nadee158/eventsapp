package com.janaka.projects.services.reports.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.util.CommonUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

public class ReportFileExportUtil {


  private static final Logger LOGGER = Logger.getLogger(ReportFileExportUtil.class);
  private static ReportFileExportUtil instance = null;
  private static final String PROP_FILE_NAME = "reports";

  /**
   * Singleton Implementation
   * 
   */
  public static ReportFileExportUtil getInstance() {
    synchronized (ReportFileExportUtil.class) {
      if (instance == null) {
        instance = new ReportFileExportUtil();
      }
      return instance;
    }
  }


  /**
   * @param type
   * @param jp
   * @param baos
   * @param fileName without the extension
   * @return
   * @throws Exception
   */
  public String export(String type, JasperPrint jp, ByteArrayOutputStream baos, String fileName) throws Exception {
    LOGGER.info("type :" + type);
    String fullFilePath = null;
    final String filePath = CommonUtil.getValueFromFile(PROP_FILE_NAME, "report.file.location");

    String filenameOriginal = fileName;
    if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_PDF)) {
      // Export to output stream
      exportPdf(jp, baos);
      // Create a new file
      // declare a custom filename
      fileName = fileName + ".pdf";
    } else if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_WORD)) {
      exportWord(jp, baos);
      fileName = fileName + ".docx";
    } else if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_WORD_TO_PDF)) {
      exportWord(jp, baos);
      fileName = fileName + ".docx";
    } else if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_ODT)) {
      exportOdt(jp, baos);
      fileName = fileName + ".odt";
    } else if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_EXCEL)) {
      exportExcel(jp, baos);
      fileName = fileName + ".xlsx";
    } else {
      exportCSV(jp, baos);
      fileName = fileName + ".csv";
    }

    new File(filePath).mkdirs();
    fullFilePath = filePath + fileName;
    LOGGER.info("fullFilePath - " + fullFilePath);
    File pdfFile = new File(fullFilePath);
    // write data to a file and store
    OutputStream outputStream = new FileOutputStream(pdfFile);
    baos.writeTo(outputStream);
    outputStream.close();
    baos.close();

    if (type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_ODT)
        || type.equalsIgnoreCase(ApplicationConstants.FILE_TYPE_WORD_TO_PDF)) {
      // CreateDoxToPdf.createPDF(filenameOriginal);
      // odtFile.delete();
      fileName = filenameOriginal + ".pdf";
    }

    return fileName;
  }



  public void exportPdf(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
    // Create a JRXlsExporter instance
    JRPdfExporter exporter = new JRPdfExporter();
    // Here assign the parameters jp and baos to the exporter
    SimpleExporterInput in = new SimpleExporterInput(jp);
    SimpleOutputStreamExporterOutput out = new SimpleOutputStreamExporterOutput(baos);
    exporter.setExporterInput(in);
    exporter.setExporterOutput(out);
    exporter.exportReport();
  }

  public void exportWord(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
    // Create a JRXlsExporter instance
    JRDocxExporter exporter = new JRDocxExporter();
    // Here assign the parameters jp and baos to the exporter
    SimpleExporterInput in = new SimpleExporterInput(jp);
    SimpleOutputStreamExporterOutput out = new SimpleOutputStreamExporterOutput(baos);
    exporter.setExporterInput(in);
    exporter.setExporterOutput(out);
    exporter.exportReport();
  }

  public void exportCSV(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
    // Create a JRXlsExporter instance
    JRCsvExporter exporter = new JRCsvExporter();
    // Here assign the parameters jp and baos to the exporter
    SimpleExporterInput in = new SimpleExporterInput(jp);
    SimpleWriterExporterOutput out = new SimpleWriterExporterOutput(baos, ApplicationConstants.CHARACTER_ENCODING_UTF8);
    exporter.setExporterInput(in);
    exporter.setExporterOutput(out);
    exporter.exportReport();
  }

  public void exportOdt(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
    // Create a JRXlsExporter instance
    JROdtExporter exporter = new JROdtExporter();
    SimpleExporterInput in = new SimpleExporterInput(jp);
    SimpleOutputStreamExporterOutput out = new SimpleOutputStreamExporterOutput(baos);
    exporter.setExporterInput(in);
    exporter.setExporterOutput(out);
    exporter.exportReport();
  }

  public void exportExcel(JasperPrint jp, ByteArrayOutputStream baos) throws JRException {
    // Create a JRXlsExporter instance
    JRXlsxExporter exporter = new JRXlsxExporter();
    SimpleExporterInput in = new SimpleExporterInput(jp);
    SimpleOutputStreamExporterOutput out = new SimpleOutputStreamExporterOutput(baos);
    exporter.setExporterInput(in);
    exporter.setExporterOutput(out);
    exporter.exportReport();
  }

  private ReportFileExportUtil() {

  }
}
