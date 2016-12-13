package com.janaka.projects.common.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.janaka.projects.common.constant.ApplicationConstants;

@Component
public class CustomJsonDateTimeSerializer extends JsonSerializer<LocalDateTime> {


  @Override
  public void serialize(LocalDateTime date, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.GLOBAL_DATE_TIME_FORMAT);
    String formattedDateTime = date.format(formatter); // "1986-04-08 12:30"
    gen.writeString(formattedDateTime);
  }

}
