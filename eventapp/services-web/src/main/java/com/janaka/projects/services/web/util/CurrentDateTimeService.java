/**
 * 
 */
package com.janaka.projects.services.web.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.janaka.projects.common.util.CommonUtil;



/**
 * @author Janaka
 *
 */
@Component("currentDateTimeService")
public class CurrentDateTimeService {

  public LocalDateTime getCurrentDateAndTime() {
    return LocalDateTime.now();
  }

  public String getFormattedCurrentDateAndTime() {
    return CommonUtil.getFormatteDate(LocalDateTime.now());
  }

}
