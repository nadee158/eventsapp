package com.janaka.projects.services.web.security.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for Spring Security.
 */
public final class SecurityUtils {


  private static final ObjectMapper mapper = new ObjectMapper();


  private SecurityUtils() {}



  public static void sendError(HttpServletResponse response, Exception exception, int status, String message)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(status);
    PrintWriter writer = response.getWriter();
    Error error = new Error("authError", exception.getMessage());
    writer.write(mapper.writeValueAsString(new Response(status, message, error)));
    writer.flush();
    writer.close();
  }


  public static void sendResponse(HttpServletResponse response, int status, Object object) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter writer = response.getWriter();
    writer.write(mapper.writeValueAsString(object));
    response.setStatus(status);
    writer.flush();
    writer.close();
  }

}
