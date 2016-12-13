package com.janaka.projects.services.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

  public String saveImegeToDisk(MultipartFile file);

  public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response);

}
