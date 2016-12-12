package com.janaka.projects.services.web.rest.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.enums.Gender;
import com.janaka.projects.services.core.CommonService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.COMMON)
public class CommonController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private CommonService commonService;


  @RequestMapping(value = ServiceEndpoints.LIST_GENDERS, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getGenderList() {
    ObjectListResponse<Gender> response = commonService.getGenderList();
    logger.info("response: " + response);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<Gender>>(HttpStatus.OK, response), HttpStatus.OK);
  }



}
