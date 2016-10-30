package com.janaka.projects.services.documentmanagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.janaka.projects.common.security.AuditContext;
import com.janaka.projects.common.security.SecurityContext;
import com.janaka.projects.dtos.requests.documentmanagement.DocumentRequest;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;

public interface DocumentMangementService {

	public DocumentResponse createDocument(DocumentRequest request, AuditContext auditContext,
			SecurityContext securityContext);

	public DocumentResponse updateDocument(DocumentRequest request, AuditContext auditContext,
			SecurityContext securityContext);

	public DocumentResponse retrieveDocument(long documentReferenceKey, SecurityContext securityContext);

	public void downloadDocument(String downloadParam, HttpServletRequest request, HttpServletResponse response,
			SecurityContext securityContext);

}
