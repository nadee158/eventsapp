package com.janaka.projects.services.business.domaindtoconverter.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.domain.core.CategorySetupItemDTO;
import com.janaka.projects.dtos.requests.core.CategoryCreationRequest;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.CategorySetupItem;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.enums.Gender;

public class CategorySetupDTOConverter {

  public static CategorySetup convertRequestToDomain(CategoryCreationRequest request, Event event) {
    if (!(request == null)) {
      CategorySetup categorySetup = new CategorySetup();
      categorySetup.setCategorySetupItems(constructCategorySetupItems(categorySetup, request.getCategorySetupItems()));
      categorySetup.setCategorySetupName(request.getCategorySetupName());
      categorySetup.setEvent(event);
      categorySetup.setFromAge(request.getFromAge());
      categorySetup.setGradeOrBelt(request.getGradeOrBelt());
      categorySetup.setToAge(request.getToAge());

      if (StringUtils.isNoneEmpty(request.getGender())) {
        categorySetup.setGender(Gender.valueOf(request.getGender()));
      }
      return categorySetup;
    }
    return null;
  }


  private static List<CategorySetupItem> constructCategorySetupItems(CategorySetup categorySetup,
      List<CategorySetupItemDTO> categorySetupItems) {
    if (!(categorySetupItems == null)) {
      List<CategorySetupItem> items = new ArrayList<CategorySetupItem>();
      categorySetupItems.forEach(item -> {
        items.add(constructCategorySetupItem(item, categorySetup));
      });
      return items;
    }
    return null;
  }


  private static CategorySetupItem constructCategorySetupItem(CategorySetupItemDTO item, CategorySetup categorySetup) {
    CategorySetupItem setupItem = new CategorySetupItem();
    setupItem.setCategorySetup(categorySetup);
    setupItem.setItemName(item.getItemName());
    return setupItem;
  }

  public static CategoryDTO convertDomainToDTO(CategorySetup categorySetup) {
    if (!(categorySetup == null)) {
      CategoryDTO dto = new CategoryDTO();
      dto.setCategorySetupItems(constructCategorySetupItemDtos(categorySetup.getCategorySetupItems()));
      dto.setCategorySetupName(categorySetup.getCategorySetupName());
      dto.setEventId(categorySetup.getEvent().getId());
      dto.setFromAge(categorySetup.getFromAge());
      dto.setGender(categorySetup.getGender().toString());
      dto.setGradeOrBelt(categorySetup.getGradeOrBelt());
      dto.setId(categorySetup.getId());
      dto.setToAge(categorySetup.getToAge());
      return dto;
    }
    return null;
  }

  private static List<CategorySetupItemDTO> constructCategorySetupItemDtos(List<CategorySetupItem> categorySetupItems) {
    if (!(categorySetupItems == null)) {
      List<CategorySetupItemDTO> dtos = new ArrayList<CategorySetupItemDTO>();
      categorySetupItems.forEach(item -> {
        dtos.add(constructCategorySetupItemDTO(item));
      });
      return dtos;
    }
    return null;
  }


  private static CategorySetupItemDTO constructCategorySetupItemDTO(CategorySetupItem item) {
    CategorySetupItemDTO dto = new CategorySetupItemDTO();
    dto.setItemName(item.getItemName());
    return dto;
  }


}
