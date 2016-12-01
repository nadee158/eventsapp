package com.janaka.projects.common.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.janaka.projects.common.constant.ApplicationConstants;

@Component
public class CustomJsonDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

  @Override
  public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(ApplicationConstants.GLOBAL_DATE_TIME_FORMAT);
    String date = jsonparser.getText();
    try {
      return LocalDateTime.parse(date, dateTimeFormatter);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
