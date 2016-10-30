package com.janaka.projects.services.web.rest.docmanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.common.security.SecurityHelper;
import com.janaka.projects.dtos.domain.documentmanagement.DocumentDTO;
import com.janaka.projects.dtos.requests.documentmanagement.DocumentRequest;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;
import com.janaka.projects.services.documentmanagement.DocumentMangementService;
import com.janaka.projects.services.web.rest.ServiceEndpoints;

@RestController
@RequestMapping(ServiceEndpoints.DOCUMENT)
public class DocumentController {

  @Autowired
  DocumentMangementService documentManagementService;


  @PreAuthorize("hasAnyRole('ROLE_A_UM_SU_UPR_VWE')")
  @RequestMapping(value = ServiceEndpoints.CREATE_DOCUMENT, method = RequestMethod.POST)
  public @ResponseBody DocumentDTO createDocument(@RequestBody MultipartFile file) {
    System.out.println(file.getOriginalFilename());

    DocumentRequest request = new DocumentRequest();
    DocumentDTO documentDTO = new DocumentDTO();
    documentDTO.setFileName(file.getOriginalFilename());
    documentDTO.setDescription("This is the test Description");
    documentDTO.setFile(file);
    request.setDocumentDTO(documentDTO);

    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();

    DocumentResponse response = documentManagementService.createDocument(request, auditContext, securityContext);
    System.out.println(response.getDocumentDTO().toString());

    return response.getDocumentDTO();
  }

  @PreAuthorize("hasAnyRole('ROLE_A_UM_SU_UPR_VWE')")
  @RequestMapping(value = ServiceEndpoints.RETRIEVE_DOCUMENT, method = RequestMethod.POST)
  public @ResponseBody DocumentDTO retrieveDocument(@RequestBody String referenceKey) {

    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();

    System.out.println("---------retrieve start--------");

    DocumentResponse retrieveResponse =
        documentManagementService.retrieveDocument(Long.parseLong(referenceKey), securityContext);
    System.out.println(retrieveResponse.getDocumentDTO().toString());

    return retrieveResponse.getDocumentDTO();
  }

  @RequestMapping(value = ServiceEndpoints.DOWNLOAD_DOCUMENT, method = RequestMethod.GET)
  public void downloadDocument(@RequestParam("udp") String downloadParam, HttpServletRequest request,
      HttpServletResponse response) {

    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();

    System.out.println("---------retrieve download--------");

    documentManagementService.downloadDocument(downloadParam, request, response, securityContext);
  }

  @RequestMapping(value = ServiceEndpoints.UPDATE_DOCUMENT, method = RequestMethod.POST)
  public @ResponseBody DocumentDTO updateDocument(@RequestBody MultipartFile file, HttpServletRequest request) {

    SecurityContext securityContext = SecurityHelper.getSecurityContext();
    AuditContext auditContext = SecurityHelper.getAuditContext();

    System.out.println("---------update start--------");

    Long referenceKey = Long.parseLong(request.getParameter("referenceKey"));

    DocumentRequest docRequest = new DocumentRequest();
    DocumentDTO documentDTO = new DocumentDTO();
    documentDTO.setReferenceKey(referenceKey);
    documentDTO.setFile(file);
    docRequest.setDocumentDTO(documentDTO);

    DocumentResponse response = documentManagementService.updateDocument(docRequest, auditContext, securityContext);
    System.out.println(response.getDocumentDTO().toString());

    return response.getDocumentDTO();
  }

}
