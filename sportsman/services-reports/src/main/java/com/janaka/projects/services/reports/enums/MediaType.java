package com.janaka.projects.services.reports.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MediaType {
	OTHER(0), DOCUMENT(1), AUDIO(2), VIDEO(3);

	private int code;

	private MediaType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	private static final Map<Integer, MediaType> LOOKUP = new HashMap<Integer, MediaType>();

	static {
		for (MediaType mediaType : EnumSet.allOf(MediaType.class)) {
			LOOKUP.put(mediaType.getCode(), mediaType);
		}
	}

	public static MediaType fromCode(int code) {
		return LOOKUP.get(code);
	}
}
