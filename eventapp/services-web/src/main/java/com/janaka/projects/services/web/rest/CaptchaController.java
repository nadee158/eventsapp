package com.janaka.projects.services.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.janaka.projects.common.captcha.CaptchaFrontEndData;
import com.janaka.projects.common.captcha.CaptchaRepository;
import com.janaka.projects.common.captcha.CaptchaSession;
import com.janaka.projects.common.captcha.CaptchaValidationRequest;
import com.janaka.projects.common.captcha.CaptchaValidationResponse;
import com.janaka.projects.common.captcha.CaptchaValidationResult;

/**
 * controller methods for captcha API
 *
 * @author by timafe on 22.05.2015.
 */

@RestController
@RequestMapping("Captcha")
public class CaptchaController {

  private Logger log = LoggerFactory.getLogger(CaptchaController.class);
  private static final int DEFAULT_NUM_OPTIONS = 5;

  @Inject
  private CaptchaSession captchaSession;

  /**
   * Get init json
   */
  @RequestMapping(value = "/start/{howMany}", method = RequestMethod.GET)
  @ResponseBody
  public CaptchaFrontEndData start(@PathVariable int howMany) {
    return captchaSession.start(howMany);
  }


  /**
   * Get Image response
   */
  @RequestMapping(value = "/image/{index}", method = RequestMethod.GET)
  // RequestParam boolean retina
  public void image(@PathVariable int index, HttpServletResponse response) {
    boolean retina = false; // TODO Implement
    InputStream input = captchaSession.getImage(index, retina);
    MediaType contentType = MediaType.IMAGE_PNG;
    writeResponse(contentType, input, response);
  }

  /**
   * Get Audio response
   */
  @RequestMapping(value = "/audio/{audioType}", method = RequestMethod.GET)
  public void audio(@PathVariable String audioType, HttpServletResponse response) {
    CaptchaRepository.AudioType audioTypEnum = CaptchaRepository.AudioType.valueOf(audioType.toUpperCase());
    // TODO exeption handling if type not found
    InputStream input = captchaSession.getAudio(audioTypEnum);
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
    writeResponse(contentType, input, response);
  }

  /**
   * Get Audio response in default mp3 format
   */
  @RequestMapping(value = "/audio", method = RequestMethod.GET)
  public void audio(HttpServletResponse response) {
    audio(CaptchaRepository.AudioType.MP3.name().toLowerCase(), response);
  }

  @RequestMapping(value = "/validate", method = RequestMethod.POST)
  @ResponseBody
  public CaptchaValidationResponse processResponse(@RequestBody CaptchaValidationRequest request) {
    CaptchaValidationResult result = captchaSession.validate(request.getCaptchaData());
    if (result.isValid()) {
      return new CaptchaValidationResponse(CaptchaValidationResponse.VALID_CAPTCHA);
    } else {
      return new CaptchaValidationResponse(CaptchaValidationResponse.INVALID_CAPTCHA);
    }
  }


  private void writeResponse(MediaType contentType, InputStream input, HttpServletResponse response) {
    OutputStream output = null;
    byte[] buffer = new byte[10240];
    try {
      response.setContentType(contentType.toString());
      output = response.getOutputStream();
      for (int length = 0; (length = input.read(buffer)) > 0;) {
        output.write(buffer, 0, length);
      }
      output.flush();
    } catch (IOException e) {
      throw new RuntimeException("Cannot load resource", e);
    } finally {
      try {
        if (output != null) {
          output.close();
        }
      } catch (IOException ignore) {
      }
      try {
        input.close();
      } catch (IOException ignore) {
      }
    }

  }

}
