package com.janaka.projects.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.janaka.projects.common.constant.ApplicationConstants;

@Component("SMSUtil")
public class SMSUtil {

  private static final Logger LOGGER = Logger.getLogger(SMSUtil.class);

  @Value("${sms.end.point.url}")
  private String endPointURL;

  public String sendSMS(String outSms, String recepient)
      throws IOException, ParserConfigurationException, SAXException {

    LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest method called.");

    LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest EPR: " + endPointURL);

    URL epr = new URL(endPointURL);
    String ackMessage = null;
    HttpURLConnection connection = null;

    try {
      connection = (HttpURLConnection) epr.openConnection();

      String soapMessage = "<?xml version='1.0' encoding='utf-8'?>"
          + "<soapenv:Envelope xmlns:soapenv=\"http://www.w3.org/2003/05/soap-envelope\">" + "<soapenv:Header>"
          + "<govsms:authData xmlns:govsms=\"http://govsms.icta.lk/\">" + "<govsms:user>icta</govsms:user>"
          + "<govsms:key>g0v5ms123</govsms:key>" + "</govsms:authData>" + "</soapenv:Header>" + "<soapenv:Body>"
          + "<ns1:SMSRequest xmlns:ns1=\"http://schemas.icta.lk/xsd/kannel/handler/v1/\">" + "<ns1:requestData>"
          + "<ns1:outSms>" + outSms + "</ns1:outSms>" + "<ns1:recepient>" + recepient + "</ns1:recepient>"
          + "<ns1:depCode>IctaTest</ns1:depCode>" + "<ns1:smscId />" + "<ns1:billable />" + "</ns1:requestData>"
          + "</ns1:SMSRequest>" + "</soapenv:Body>" + "</soapenv:Envelope>";

      LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest 222222.");

      connection.setRequestProperty("Accept-Encoding", "gzip,deflate");
      connection.setRequestProperty("Content-Type", "application/soap+xml;charset=UTF-8;action=\"urn:mediate\"");
      connection.setRequestProperty("Content-Length", String.valueOf(soapMessage.length()));
      connection.setRequestProperty("Host", "lankagate.gov.lk:9080");
      connection.setRequestProperty("Connection", "Keep-Alive");
      connection.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
      // connection.setRequestProperty("SoapAction", "");
      connection.setDoOutput(true);

      PrintWriter pw = new PrintWriter(connection.getOutputStream());
      pw.write(soapMessage);
      pw.flush();
      connection.getOutputStream().close();

    } catch (Exception e1) {
      LOGGER.warn("MEPASMSWSInvorkerClient :: Exception occurred at FLUSHING - " + e1.getMessage());
      e1.printStackTrace();
    }
    LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest FLUSHED.");

    java.io.BufferedReader rd = null;
    try {
      rd = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
    } catch (Exception e) {
      LOGGER.error("MEPASMSWSInvorkerClient :: Exception occurred - " + e.getMessage());
      e.printStackTrace();
    }

    String successMsg = "GovSMS delivery success.";
    String line;

    while ((line = rd.readLine()) != null) {
      LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest inside rd.readLine loop.");
      LOGGER.warn("MEssage 1 +++++++++++++++++ " + line);
      if (line.indexOf(successMsg) > 0) {
        LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest ackMessage is success.");
        LOGGER.warn("MEssage 2 +++++++++++++++++ " + line);
        ackMessage = ApplicationConstants.SUCCESS;

      } else {
        LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest ackMessage is unsuccess.");
        ackMessage = ApplicationConstants.ERROR;
      }
    }
    LOGGER.warn("MEssage 3 +++++++++++++++++ " + line);
    LOGGER.warn("MEPASMSWSInvorkerClient :: sendSMSRequest method called successfully.");
    return ackMessage;

  }

}
