package com.janaka.projects.services.reports.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.janaka.projects.services.reports.domains.ReportTemplate;


@Component("reportTemplateValidator")
public class ReportTemplateValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return ReportTemplate.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ReportTemplate reportTemplate = (ReportTemplate) target;

    if (reportTemplate.getFile().getSize() == 0) {
      errors.rejectValue("file", "required.fileUpload");
    }

    if (StringUtils.isEmpty(reportTemplate.getReportName())) {
      errors.rejectValue("reportName", "required.reportName");
    }

  }

}
