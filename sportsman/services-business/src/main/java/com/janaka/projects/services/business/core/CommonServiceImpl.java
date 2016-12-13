package com.janaka.projects.services.business.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.janaka.projects.common.constant.ApplicationConstants;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.entitymanagement.enums.Gender;
import com.janaka.projects.services.business.common.BusinessService;
import com.janaka.projects.services.core.CommonService;

@Service("commonService")
public class CommonServiceImpl extends BusinessService implements CommonService {

  @Override
  public ObjectListResponse<Gender> getGenderList() {
    ObjectListResponse<Gender> response = new ObjectListResponse<Gender>();
    Map<Integer, Gender> genderMap = Gender.getLookup();

    List<Gender> genderList = genderMap.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());

    response.setDtoList(genderList);
    response.setListSize(genderList.size());
    response.setMessage("success");
    response.setStatus(ApplicationConstants.STATUS_CODE_OK);
    return response;
  }



}
