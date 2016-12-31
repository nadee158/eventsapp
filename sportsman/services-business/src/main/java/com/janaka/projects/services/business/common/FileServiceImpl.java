package com.janaka.projects.services.business.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.services.common.FileService;

@Service(value = "fileService")
@Transactional()
public class FileServiceImpl extends BusinessService implements FileService {

  @Value("${rdd.document.manager.upload.location}")
  private String uploadsDir;

  @Override
  public String saveImegeToDisk(MultipartFile file) {
    if (!file.isEmpty()) {
      try {
        if (!new File(uploadsDir).exists()) {
          new File(uploadsDir).mkdirs();
        }

        System.out.println("realPathtoUploads = {}" + uploadsDir);


        String fileName = createUploadedFileName(file);

        System.out.println("fileNameb " + fileName);

        String filePath = uploadsDir + "/" + fileName;

        File dest = new File(filePath);

        FileOutputStream fos = null;
        try {
          dest.createNewFile();
          fos = new FileOutputStream(dest);
          IOUtils.copy(file.getInputStream(), fos);
        } catch (FileNotFoundException e) {
          System.out.println("File conversion error");
          e.printStackTrace();
        } catch (IOException e) {
          System.out.println("File conversion error");
          e.printStackTrace();
        } finally {
          if (fos != null) {
            try {
              fos.close();
            } catch (IOException e) {
              System.out.println("File conversion error");
              e.printStackTrace();
            }
          }
        }


        return fileName;

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private String createUploadedFileName(MultipartFile file) {
    String randomPrefix = StringUtils.substring(UUID.randomUUID().toString(), 0, 5);
    System.out.println("randomPrefix :" + randomPrefix);
    String originalName = file.getOriginalFilename();
    System.out.println("originalName :" + originalName);
    if (originalName.length() > 10) {
      originalName = StringUtils.substring(originalName, (originalName.length() - 5), originalName.length());
    }
    System.out.println("originalName after trim :" + originalName);
    randomPrefix = randomPrefix + "_" + originalName;
    System.out.println("final :" + randomPrefix);
    return randomPrefix;
  }

  @Override
  public void downloadFile(String fileName, HttpServletRequest request, HttpServletResponse response) {
    if (StringUtils.isEmpty(fileName)) {
      pushErrorResponse("4008", "invalid parameter; file name cannot be generated", response);
    } else {
      try {
        String filePath = uploadsDir;
        String fileURL = filePath + "/" + fileName;
        File file = new File(fileURL);

        if (!file.exists()) {
          pushErrorResponse("4004", "file is not found", response);
        }

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
          System.out.println("mimetype is not detectable, will take default");
          mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        /*
         * "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything
         * viewable by browser] right on browser while others(zip e.g) will be directly downloaded
         * [may provide save as popup, based on your browser setting.]
         */
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));

        /*
         * "Content-Disposition : attachment" will be directly download, may provide save as popup,
         * based on your browser setting
         */

        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        // Copy bytes from source to destination(outputstream in this
        // example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("IOError writing file to output stream");
      }
    }

  }

  private void pushErrorResponse(String errorCode, String errorMessage, HttpServletResponse response) {
    try {
      String pushError = "{\"ErrorCode\":\"" + errorCode + "\", \"Message\":\"" + errorMessage + "\"}";
      System.out.println(pushError);
      OutputStream outputStream = response.getOutputStream();
      outputStream.write(pushError.getBytes(Charset.forName("UTF-8")));
      outputStream.close();
      return;
    } catch (Exception e) {
      e.printStackTrace();
    }

  }



}
