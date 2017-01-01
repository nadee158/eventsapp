package com.janaka.projects.services.business.domaindtoconverter.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.janaka.projects.dtos.domain.core.CategoryDTO;
import com.janaka.projects.dtos.domain.core.CategorySetupItemDTO;
import com.janaka.projects.dtos.requests.core.CategoryCreationRequest;
import com.janaka.projects.dtos.requests.core.CategoryUpdateRequest;
import com.janaka.projects.entitymanagement.domain.core.AgeGroup;
import com.janaka.projects.entitymanagement.domain.core.CategorySetup;
import com.janaka.projects.entitymanagement.domain.core.CategorySetupItem;
import com.janaka.projects.entitymanagement.domain.core.Event;
import com.janaka.projects.entitymanagement.domain.core.GradeBelt;
import com.janaka.projects.entitymanagement.enums.Gender;
import com.janaka.projects.entitymanagement.enums.RecordStatus;

public class CategorySetupDTOConverter {

  public static CategorySetup convertRequestToDomain(CategoryCreationRequest request, Event event, AgeGroup ageGroup,
      GradeBelt gradeBelt) {
    if (!(request == null)) {
      CategorySetup categorySetup = new CategorySetup();
      categorySetup.setCategorySetupItems(constructCategorySetupItems(categorySetup, request.getCategorySetupItems()));
      categorySetup.setCategorySetupName(request.getCategorySetupName());
      categorySetup.setEvent(event);
      categorySetup.setAgeGroup(ageGroup);

      categorySetup.setGradeOrBelt(gradeBelt);


      if (StringUtils.isNoneEmpty(request.getGender())) {
        categorySetup.setGender(Gender.valueOf(request.getGender()));
      }
      return categorySetup;
    }
    return null;
  }


  private static List<CategorySetupItem> constructCategorySetupItems(CategorySetup categorySetup,
      List<CategorySetupItemDTO> categorySetupItems) {
    List<CategorySetupItem> items = categorySetup.getCategorySetupItems();
    if (!(categorySetupItems == null)) {
      if (items == null) {
        items = new ArrayList<CategorySetupItem>();
      }
      items.clear();
      for (CategorySetupItemDTO itemDTO : categorySetupItems) {
        items.add(constructCategorySetupItem(itemDTO, categorySetup));
      }
      return items;
    }
    return null;
  }


  private static CategorySetupItem constructCategorySetupItem(CategorySetupItemDTO item, CategorySetup categorySetup) {
    CategorySetupItem setupItem = new CategorySetupItem();
    setupItem.setCategorySetup(categorySetup);
    setupItem.setItemName(item.getItemName());
    setupItem.setText1(item.getText1());
    setupItem.setText2(item.getText2());
    return setupItem;
  }

  public static CategoryDTO convertDomainToDTO(CategorySetup categorySetup) {
    if (!(categorySetup == null)) {
      CategoryDTO dto = new CategoryDTO();
      dto.setCategorySetupItems(constructCategorySetupItemDtos(categorySetup.getCategorySetupItems()));
      dto.setCategorySetupName(categorySetup.getCategorySetupName());
      dto.setEventId(categorySetup.getEvent().getId());
      dto.setFromAge(categorySetup.getAgeGroup().getFromAge());
      dto.setAgeGroupId(categorySetup.getAgeGroup().getId());
      dto.setGender(categorySetup.getGender().toString());
      dto.setGradeOrBeltId(categorySetup.getGradeOrBelt().getId());
      dto.setGradeOrBeltName(categorySetup.getGradeOrBelt().getGradeBeltName());
      dto.setId(categorySetup.getId());
      dto.setToAge(categorySetup.getAgeGroup().getToAge());
      dto.setEventName(categorySetup.getEvent().getEventName());
      dto.setRecordStatus(categorySetup.getRecordStatus().getRecordStatusCode());
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
    dto.setText1(item.getText1());
    dto.setText2(item.getText2());
    return dto;
  }


  public static CategorySetup updateDomainFromRequest(CategoryUpdateRequest request, CategorySetup categorySetupFromDb,
      Event event, AgeGroup ageGroup, GradeBelt gradeBelt) {
    categorySetupFromDb.setAgeGroup(ageGroup);
    if (!(categorySetupFromDb.getCategorySetupItems() == null)) {
      categorySetupFromDb.getCategorySetupItems().clear();
    }
    categorySetupFromDb
        .setCategorySetupItems(constructCategorySetupItems(categorySetupFromDb, request.getCategorySetupItems()));
    categorySetupFromDb.setCategorySetupName(request.getCategorySetupName());
    categorySetupFromDb.setEvent(event);
    if (StringUtils.isNoneEmpty(request.getGender())) {
      categorySetupFromDb.setGender(Gender.valueOf(request.getGender()));
    }
    categorySetupFromDb.setGradeOrBelt(gradeBelt);
    if (StringUtils.isNoneEmpty(request.getRecordStatus())) {
      categorySetupFromDb.setRecordStatus(RecordStatus.fromRecordStatusCode(request.getRecordStatus()));
    }
    return categorySetupFromDb;
  }


}
