package com.janaka.projects.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class CustomJsonDateSerializer extends JsonSerializer<Date> {


  @Override
  public void serialize(Date date, JsonGenerator gen, SerializerProvider serializers)
      throws IOException, JsonProcessingException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDate = dateFormat.format(date);
    gen.writeString(formattedDate);
  }

}
