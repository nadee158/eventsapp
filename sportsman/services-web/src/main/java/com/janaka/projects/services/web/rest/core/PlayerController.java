package com.janaka.projects.services.web.rest.core;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.common.datamanagement.TabularDataRequestModel;
import com.janaka.projects.common.datamanagement.TabularDataResponseModel;
import com.janaka.projects.dtos.domain.core.ApiResponseObject;
import com.janaka.projects.dtos.domain.core.CustomResponseEntity;
import com.janaka.projects.dtos.domain.core.PlayerDTO;
import com.janaka.projects.dtos.requests.common.ObjectDeletionRequest;
import com.janaka.projects.dtos.requests.common.ObjectRetrievalRequest;
import com.janaka.projects.dtos.requests.core.PlayerCreationRequest;
import com.janaka.projects.dtos.requests.core.PlayerUpdateRequest;
import com.janaka.projects.dtos.responses.common.ObjectDeletionResponse;
import com.janaka.projects.dtos.responses.common.ObjectListResponse;
import com.janaka.projects.dtos.responses.common.ObjectRetrievalResponse;
import com.janaka.projects.dtos.responses.core.PlayerCreationResponse;
import com.janaka.projects.dtos.responses.core.PlayerUpdateResponse;
import com.janaka.projects.entitymanagement.enums.SequenceNumberType;
import com.janaka.projects.services.common.FileService;
import com.janaka.projects.services.common.SequenceNumberService;
import com.janaka.projects.services.core.PlayerService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.PLAYERS)
public class PlayerController {

  private Logger logger = Logger.getLogger(this.getClass());

  @Autowired
  private PlayerService playerService;

  @Autowired
  private SequenceNumberService sequenceNumberService;

  @Autowired
  private FileService fileService;


  @RequestMapping(value = ServiceEndpoints.CREATE, method = RequestMethod.POST,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CustomResponseEntity<ApiResponseObject<?>> createPlayer(
      @RequestPart(value = "playerCreationRequest") PlayerCreationRequest request,
      @RequestPart("file") MultipartFile file) {
    logger.error("request : " + request);
    logger.error("file : " + file);
    request.setFile(file);
    request.setSavedImagePath(fileService.saveImegeToDisk(request.getFile()));
    request.setPlayerNumber(sequenceNumberService.getNextReferenceNumberByNumberType(SequenceNumberType.PLAYER_NUMBER));
    PlayerCreationResponse response = playerService.createPlayer(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<PlayerCreationResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE, method = RequestMethod.PUT)
  public CustomResponseEntity<ApiResponseObject<?>> updatePlayer(@Valid @RequestBody PlayerUpdateRequest request) {
    logger.error("request : " + request);
    PlayerUpdateResponse response = playerService.updatePlayer(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<PlayerUpdateResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE_WITH_FILE, method = RequestMethod.POST,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody CustomResponseEntity<ApiResponseObject<?>> updateWithFile(
      @RequestPart(value = "playerUpdateRequest") PlayerUpdateRequest request,
      @RequestPart("file") MultipartFile file) {
    logger.error("request : " + request);
    logger.error("file : " + file);
    request.setFile(file);
    request.setSavedImagePath(fileService.saveImegeToDisk(request.getFile()));
    System.out.println("request.getSavedImagePath() " + request.getSavedImagePath());
    PlayerUpdateResponse response = playerService.updatePlayer(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<PlayerUpdateResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }



  @RequestMapping(value = ServiceEndpoints.DELETE, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> deletePlayer(@RequestBody ObjectDeletionRequest request) {
    logger.error("request : " + request);
    ObjectDeletionResponse response = playerService.deletePlayer(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectDeletionResponse>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ACTIVE_LIST, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUsers() {
    ObjectListResponse<PlayerDTO> response = playerService.getAllActivePlayers();
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectListResponse<PlayerDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_BY_ID, method = RequestMethod.POST)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveSecurityUserById(
      @RequestBody ObjectRetrievalRequest request) {
    ObjectRetrievalResponse<PlayerDTO> response = playerService.getPlayerById(request);
    return new CustomResponseEntity<ApiResponseObject<?>>(
        new ApiResponseObject<ObjectRetrievalResponse<PlayerDTO>>(HttpStatus.OK, response), HttpStatus.OK);
  }

  @RequestMapping(value = ServiceEndpoints.GET_ALL, method = RequestMethod.POST)
  public TabularDataResponseModel<PlayerDTO> getActiveCategories(@RequestBody TabularDataRequestModel request) {
    return playerService.getPlayers(request);
  }

  @RequestMapping(value = ServiceEndpoints.COUNT, method = RequestMethod.GET)
  public CustomResponseEntity<ApiResponseObject<?>> getActiveCount() {
    long count = playerService.getActiveCount();
    return new CustomResponseEntity<ApiResponseObject<?>>(new ApiResponseObject<Long>(HttpStatus.OK, count),
        HttpStatus.OK);
  }

}
