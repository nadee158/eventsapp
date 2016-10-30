package com.janaka.projects.dtos.responses.documentmanagement;

import com.janaka.projects.dtos.domain.documentmanagement.DocumentDTO;
import com.janaka.projects.dtos.responses.common.BaseResponse;

public class DocumentRetrieveResponse extends BaseResponse {

	private static final long serialVersionUID = 1L;

	private DocumentDTO documentDTO;

	public DocumentDTO getDocumentDTO() {
		return documentDTO;
	}

	public void setDocumentDTO(DocumentDTO documentDTO) {
		this.documentDTO = documentDTO;
	}

}
