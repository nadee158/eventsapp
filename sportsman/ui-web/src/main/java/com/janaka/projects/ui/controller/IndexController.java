package com.janaka.projects.ui.controller;

import java.util.Calendar;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

  @Value("${application.version}")
  private String version;

  @Value("${application.isdebug}")
  private boolean isDebug;

  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("v", getVersion());
    return "index";
  }
  
  
  private String getVersion() {
    String version = "";

    if (isDebug) {
      version = this.version + "_" + Calendar.getInstance().getTimeInMillis();
    } else {
      version = this.version;
    }

    return version;
  }

}
