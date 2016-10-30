package com.janaka.projects.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.common.documentmanagement.FileMetaData;

public class FileUtil {

  public static FileMetaData getFileMetaData(MultipartFile multiPartFile, String locationURL) {
    try {
      String originalFileName = multiPartFile.getOriginalFilename();
      String extension = originalFileName.split("\\.")[1];
      String generatedFileName = generateFileName(extension);
      String filePath = locationURL + "/" + generatedFileName;
      String mimeType = null;
      long fileSize = multiPartFile.getSize();

      File convFile = new File(filePath);
      convFile.getParentFile().mkdirs();
      convFile.createNewFile();
      FileOutputStream fos = new FileOutputStream(convFile);
      fos.write(multiPartFile.getBytes());
      fos.close();

      mimeType = getMimeType(multiPartFile);
      extension = getExtension(filePath);

      FileMetaData fileMetaData = new FileMetaData();
      fileMetaData.setOriginalFileName(originalFileName);
      fileMetaData.setGeneratedFileName(generatedFileName);
      fileMetaData.setFilePath(filePath);
      fileMetaData.setMimeType(mimeType);
      fileMetaData.setExtension(extension);
      fileMetaData.setFileSize(fileSize);
      return fileMetaData;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public static String generateFileName(String extension) {
    return UUID.randomUUID().toString() + "." + extension;
  }

  public static String getMimeType(MultipartFile file) {
    // try {
    // MagicMatch match = Magic.getMagicMatch(new File(filePath), true);
    // return match.getMimeType();
    // } catch (MagicParseException | MagicMatchNotFoundException | MalformedURIException e) {
    // e.printStackTrace();
    // return "unknown";
    // }
    System.out.println("--------file content type---------" + file.getContentType());
    return file.getContentType();
  }

  public static String getExtension(String filePath) {
    // try {
    // MagicMatch match = Magic.getMagicMatch(new File(filePath), true);
    // return match.getExtension();
    // } catch (MagicParseException | MagicMatchNotFoundException | MagicException e) {
    // e.printStackTrace();
    // return filePath.split("\\.")[1];
    // }
    return filePath.split("\\.")[1];
  }

  public static int getMediaTypeByMimeType(String mimeType) {

    String[] documentMimeTypes = {"", "", ""};

    String[] audioMimeTypes = {"", "", ""};

    String[] videoMimeTypes = {"", "", ""};

    if (useLoop(documentMimeTypes, mimeType)) {
      return 1;
    } else if (useLoop(audioMimeTypes, mimeType)) {
      return 2;
    } else if (useLoop(videoMimeTypes, mimeType)) {
      return 3;
    } else {
      return 0;
    }
  }

  public static boolean useLoop(String[] arr, String targetValue) {
    for (String s : arr) {
      if (s.trim().equals(targetValue.trim()))
        return true;
    }
    return false;
  }

}
