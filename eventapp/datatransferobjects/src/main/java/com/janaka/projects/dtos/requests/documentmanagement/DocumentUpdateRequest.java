package com.janaka.projects.dtos.requests.documentmanagement;

import java.io.Serializable;

import com.janaka.projects.dtos.domain.documentmanagement.DocumentDTO;

public class DocumentUpdateRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private DocumentDTO documentDTO;

	public DocumentDTO getDocumentDTO() {
		return documentDTO;
	}

	public void setDocumentDTO(DocumentDTO documentDTO) {
		this.documentDTO = documentDTO;
	}

}
