package com.janaka.projects.services.business.domaindtoconverter.documentmanagement;

import com.janaka.projects.dtos.domain.documentmanagement.DocumentDTO;
import com.janaka.projects.dtos.requests.documentmanagement.DocumentRequest;
import com.janaka.projects.dtos.responses.documentmanagement.DocumentResponse;
import com.janaka.projects.entitymanagement.domain.documentmanagement.Media;
import com.janaka.projects.entitymanagement.enums.MediaDomainType;

public class MediaDTOConverter {

	public static Media convertRequestToDomain(DocumentRequest request) {
		if (request != null) {
			Media media = new Media();
			media.setName(request.getDocumentDTO().getFileName());
			media.setDescription(request.getDocumentDTO().getDescription());
			MediaDomainType mediaDomainType = MediaDomainType.fromCode(request.getDocumentDTO().getMediaDomainType());
			if (mediaDomainType != null) {
				media.setMediaDomain(mediaDomainType);
			} else {
				media.setMediaDomain(MediaDomainType.OTHER);
			}
			return media;
		}
		return null;
	}

	public static DocumentResponse convertDomainToResponse(Media media) {
		DocumentResponse response = new DocumentResponse();
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setReferenceKey(media.getId());
		documentDTO.setFileName(media.getName());
		documentDTO.setDescription(media.getDescription());
		documentDTO.setMediaDomainType(media.getMediaDomain().getCode());
		documentDTO.setFileSize(media.getMediaFormat().getFileSize());
		documentDTO.setGeneratedFileName(media.getGeneratedFileName());
		documentDTO.setOriginalFileName(media.getOriginalFileName());
		response.setDocumentDTO(documentDTO);
		return response;
	}

}
