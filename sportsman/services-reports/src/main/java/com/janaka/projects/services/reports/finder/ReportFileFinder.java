package com.janaka.projects.services.reports.finder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.common.util.CommonUtil;


/**
 * Servlet implementation class ReportFileFinder
 */
public class ReportFileFinder extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String PROP_FILE_NAME = "reports";
  private final int BUFSIZE = 1024 * 1024;
  private final String filePath = CommonUtil.getValueFromFile(PROP_FILE_NAME, "report.file.location");

  /**
   * @see HttpServlet#HttpServlet()
   */
  public ReportFileFinder() {
    super();
    // TODO Auto-generated constructor stub
  }


  public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String fileName = request.getParameter("fileName");
    if (StringUtils.isEmpty(fileName)) {
      fileName = ApplicationConstants.REPORT_NOT_FOUND_IMAGE;
    }
    String fullFilePath = filePath + "/" + fileName;
    File f = new File(fullFilePath);
    if (!(f.exists())) {
      f = new File(filePath + "/" + ApplicationConstants.REPORT_NOT_FOUND_IMAGE);
    }
    int length = 0;
    // Prepare streams.
    BufferedInputStream input = null;
    BufferedOutputStream output = null;
    try {
      ServletContext context = request.getSession().getServletContext();
      String mimetype = context.getMimeType(fullFilePath);
      System.out.println("mimetype :" + mimetype);

      response.setContentType((mimetype != null) ? mimetype : "application/octet-stream");
      response.setContentLength((int) f.length());
      response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");

      input = new BufferedInputStream(new FileInputStream(f), BUFSIZE);
      output = new BufferedOutputStream(response.getOutputStream(), BUFSIZE);

      // Write file contents to response.
      byte[] buffer = new byte[BUFSIZE];
      while ((length = input.read(buffer)) > 0) {
        output.write(buffer, 0, length);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      output.flush();
      output.close();
      input.close();
    }


  }


  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

}
