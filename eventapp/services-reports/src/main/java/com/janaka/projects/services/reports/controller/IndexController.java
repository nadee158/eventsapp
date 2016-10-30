package com.janaka.projects.services.reports.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.janaka.projects.common.util.CommonUtil;
import com.janaka.projects.services.reports.domains.ReportTemplate;
import com.janaka.projects.services.reports.services.ReportService;
import com.janaka.projects.services.reports.util.ReportFileUtil;
import com.janaka.projects.services.reports.validator.ReportTemplateValidator;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReport;

@Controller
public class IndexController {

  private static final String PROP_FILE_NAME = "reports";
  private static String UPLOAD_LOCATION = CommonUtil.getValueFromFile(PROP_FILE_NAME, "report.jrxml.template.location");

  @Autowired
  private ReportService reportService;

  @Value("${application.version}")
  private String version;

  @Value("${application.isdebug}")
  private boolean isDebug;

  @Autowired
  private ReportTemplateValidator reportTemplateValidator;

  @InitBinder("reportTemplate")
  protected void initBinderFileBucket(WebDataBinder binder) {
    binder.setValidator(reportTemplateValidator);
  }


  @RequestMapping("/")
  public String welcome(Map<String, Object> model) {
    model.put("v", getVersion());
    try {
      model.put("reportTemplateList", reportService.getAllReportTemplates());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "home";
  }

  @RequestMapping("/home")
  public String home(Map<String, Object> model, @RequestParam(name = "message", required = false) String message) {
    model.put("v", getVersion());
    try {
      model.put("reportTemplateList", reportService.getAllReportTemplates());
      model.put("message", message);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "home";
  }

  @RequestMapping(value = "/addReportTemplate", method = RequestMethod.GET)
  public String addReportTemplate(Map<String, Object> model) {
    model.put("v", getVersion());
    model.put("reportTemplate", new ReportTemplate());
    return "addReportTemplate";
  }

  @RequestMapping(value = "/editReportTemplate", method = RequestMethod.GET)
  public ModelAndView editReportTemplate(@RequestParam("id") long reportId) {
    Map<String, Object> model = new ModelMap();
    model.put("v", getVersion());
    try {
      model.put("reportTemplate", reportService.getReportReportTemplateById(reportId));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ModelAndView("editReportTemplate", model);
  }


  @RequestMapping(value = {"/saveReportTemplate"}, method = RequestMethod.POST)
  public String saveReportTemplate(@Valid @ModelAttribute("reportTemplate") ReportTemplate reportTemplate,
      BindingResult result, ModelMap model, HttpServletRequest request) {
    if (result.hasErrors()) {
      System.out.println("validation errors in multi upload");
      return "addReportTemplate";
    } else {
      System.out.println("reportTemplate " + reportTemplate);
      MultipartFile multipartFile = reportTemplate.getFile();
      System.out.println("reportTemplate.getFile() " + reportTemplate.getFile());
      String fileName = "";

      if (multipartFile != null) {
        fileName = multipartFile.getOriginalFilename();
        reportTemplate.setOriginalFileName(fileName);
        // Now do something with file...
        String uniqueFileName =
            "rprt_" + System.currentTimeMillis() + "." + multipartFile.getOriginalFilename().split("\\.")[1];;
        try {
          File file = new File(UPLOAD_LOCATION);
          if (!(file.exists())) {
            file.mkdirs();
          }
          FileCopyUtils.copy(reportTemplate.getFile().getBytes(), new File(UPLOAD_LOCATION + uniqueFileName));
          reportTemplate.setUploadedFileName(uniqueFileName);
          Authentication auth = SecurityContextHolder.getContext().getAuthentication();
          if (!(auth == null)) {
            reportTemplate.setCreatedByUser(auth.getName());
          }
          reportTemplate.setCreationTime(Calendar.getInstance().getTime());

          reportService.saveReportTemplate(reportTemplate);
        } catch (IOException e) {
          e.printStackTrace();
          System.out.println("erro while saving file");
          return "addReportTemplate";
        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("erro while saving record");
          return "addReportTemplate";
        }
        System.out.println("fileName " + fileName);
      }
    }
    return "redirect:/home?message=successfully saved";
  }

  @RequestMapping(value = {"/updateReportTemplate"}, method = RequestMethod.POST)
  public String updateReportTemplate(@ModelAttribute("reportTemplate") ReportTemplate reportTemplate,
      BindingResult result, ModelMap model) {
    System.out.println("reportTemplate " + reportTemplate);
    MultipartFile multipartFile = reportTemplate.getFile();
    System.out.println("reportTemplate.getFile() " + reportTemplate.getFile());
    String fileName = "";

    if (multipartFile != null) {
      fileName = multipartFile.getOriginalFilename();
      reportTemplate.setOriginalFileName(fileName);
      String uniqueFileName = "rprt_" + System.currentTimeMillis();
      if (!(multipartFile.getOriginalFilename() == null && multipartFile.getOriginalFilename().contains("\\."))) {
        // Now do something with file...
        uniqueFileName = uniqueFileName + "." + multipartFile.getOriginalFilename().split("\\.")[1];;
      }

      try {
        File file = new File(UPLOAD_LOCATION);
        if (!(file.exists())) {
          file.mkdirs();
        }
        FileCopyUtils.copy(reportTemplate.getFile().getBytes(), new File(UPLOAD_LOCATION + uniqueFileName));
        reportTemplate.setUploadedFileName(uniqueFileName);
        System.out.println("fileName " + fileName);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("erro while saving file");
        return "editReportTemplate";
      }
    }

    try {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (!(auth == null)) {
        reportTemplate.setModifiedByUser(auth.getName());
      }
      reportTemplate.setModificationTime(Calendar.getInstance().getTime());
      reportService.updateReportTemplate(reportTemplate);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("erro while update record");
      return "editReportTemplate";
    }

    return "redirect:/home?message=successfully updaed";
  }

  @RequestMapping(value = "getReportParam", method = RequestMethod.GET)
  public ModelAndView getReportParam(@RequestParam("jasperName") String fileName, HttpServletRequest request,
      HttpServletResponse response) {
    JasperReport jasperReport = null;
    ModelMap map = new ModelMap();
    ArrayList<String> paramList = new ArrayList<String>();

    jasperReport = ReportFileUtil.getCustomJasperReport(fileName);
    JRParameter[] params = jasperReport.getParameters();

    for (JRParameter param : params) {
      if (!param.isSystemDefined() && param.isForPrompting()) {
        System.out.println("---" + param.getName());
        paramList.add(param.getName());
        System.out.println(">>>>>>>>>>>>>>>" + param.getDescription());
        System.out.println(">>>>>>>>>>>>>>>" + param.getDefaultValueExpression());
        System.out.println(">>>>>>>>>>>>>>>" + param.getNestedTypeName());
      }
    }
    map.addAttribute("paramList", paramList);
    map.addAttribute("fileName", fileName);
    return new ModelAndView("paramManager", map);
  }

  @RequestMapping(value = "getReportParam", method = RequestMethod.POST)
  public @ResponseBody boolean getReportParamPost(HttpServletRequest request, HttpServletResponse response,
      HttpSession session) {

    System.out.println("finalString" + request.getParameter("finalString"));
    String finalString = request.getParameter("finalString");

    System.out.println("fileName" + request.getParameter("fileName"));
    String fileName = request.getParameter("fileName");

    String[] paramList = null;
    paramList = finalString.split("#");

    Map<String, Object> parameters = new HashMap<String, Object>();

    for (String string : paramList) {
      parameters.put(string.split("_")[0], string.split("_")[1]);
    }

    try {
      reportService.printCustomReports(fileName, parameters, response);

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

  }

  @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
  public String loadLogin(ModelMap model) {
    return "login";
  }

  @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
  public String signUp(ModelMap model) {
    return "signUp";
  }

  @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
  public String register(ModelMap model) {

    return "redirect:/login";
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
