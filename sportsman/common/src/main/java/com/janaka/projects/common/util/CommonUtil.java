package com.janaka.projects.common.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.janaka.projects.common.constant.ApplicationConstants;

/**
 * This class is responsible to provide common framework level functionality.
 * 
 * @author Koushik Mondal
 * 
 */
public final class CommonUtil {

  private static final Logger LOGGER = Logger.getLogger(CommonUtil.class);
  private static Map<String, Properties> fileNamePropertiesMapping = new HashMap<String, Properties>();

  /**
   * To check whether a string is empty or null
   * 
   * @param strValue
   * @return true if empty or null
   */
  public static boolean checkBlank(String strValue) {
    return (strValue == null || strValue.trim().isEmpty());
  }

  /**
   * validate e mail address
   * 
   * @param email - user email address
   * @return boolean - e mail address valid or invalid
   */
  public static boolean checkMail(String email) {
    String expression = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(email);
    if (matcher.matches()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * validate mobile number
   * 
   * @param mobile number
   * @return boolean - true if valid false if not
   */
  public static boolean checkMobile(String mobile) {
    if (mobile != null) {
      // ^[\\d{15}+]+$ - new
      // \\d{10} - old
      Pattern pattern = Pattern.compile("^[\\d{15}+]+$");
      Matcher matcher = pattern.matcher(mobile.trim());
      if (matcher.matches()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * To get all the key value pairs from the property file
   * 
   * @param filePath
   * @return Properties
   */
  public static Properties getPropertiesFromFile(String filePath) {

    Properties properties = new Properties();

    if (fileNamePropertiesMapping.containsKey(filePath)) {
      properties = fileNamePropertiesMapping.get(filePath);
    } else {
      try {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(filePath);
        LOGGER.debug("loaded local Resource Bundle File:" + filePath);
        String key = null;
        if (resourceBundle != null) {
          Enumeration<String> localenum = resourceBundle.getKeys();
          while (localenum.hasMoreElements()) {
            key = localenum.nextElement();
            properties.put(key, resourceBundle.getString(key));
          }
        }

        fileNamePropertiesMapping.put(filePath, properties);

      } catch (MissingResourceException ex) {
        LOGGER.fatal("could not find file:" + ex);
      }
    }

    return properties;
  }

  /**
   * To get the value of a particular key from a specified properties file
   * 
   * @param filePath
   * @param key
   * @return the value
   */
  public static String getValueFromFile(String filePath, String key) {
    return getPropertiesFromFile(filePath).getProperty(key);
  }

  public static int getPreviousYear() {
    Calendar prevYear = Calendar.getInstance();
    prevYear.add(Calendar.YEAR, -1);
    return prevYear.get(Calendar.YEAR);
  }

  // No initialization allowed
  private CommonUtil() {

  }

  public static Date getSqlDateFromUtilDate(java.util.Date utilDate) {
    if (!(utilDate == null)) {
      return new Date(utilDate.getTime());
    }
    return null;
  }

  public static Timestamp getSqlTimeStampFromUtilDate(java.util.Date utilDate) {
    if (!(utilDate == null)) {
      return new Timestamp(utilDate.getTime());
    }
    return null;
  }

  public static String getFileTypeFromExtension(String extension) {
    if (!(extension == null)) {
      if (extension.toLowerCase().equals(ApplicationConstants.EXTENSION_TYPE_PDF)) {
        return ApplicationConstants.FILE_TYPE_PDF;
      }
      if (extension.toLowerCase().equals(ApplicationConstants.EXTENSION_TYPE_WORD_NEW)
          || extension.toLowerCase().equals(ApplicationConstants.EXTENSION_TYPE_WORD_OLD)) {
        return ApplicationConstants.FILE_TYPE_WORD;
      }
      if (extension.toLowerCase().equals(ApplicationConstants.EXTENSION_TYPE_JPG)
          || extension.toLowerCase().equals(ApplicationConstants.EXTENSION_TYPE_PNG)) {
        return ApplicationConstants.FILE_TYPE_IMAGE;
      }
    }
    return null;
  }


  public static String getFormatteDate(LocalDateTime localDateTime) {
    if (!(localDateTime == null)) {
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(ApplicationConstants.GLOBAL_DATE_TIME_FORMAT);
      return localDateTime.format(dateFormat);
    }
    return null;
  }

  public static LocalDateTime getParsedDate(String dateString) {
    if (StringUtils.isNotEmpty(dateString)) {
      dateString = StringUtils.upperCase(dateString);
      DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(ApplicationConstants.GLOBAL_DATE_TIME_FORMAT);
      return LocalDateTime.parse(dateString, dateFormat);
    }
    return null;
  }

}
