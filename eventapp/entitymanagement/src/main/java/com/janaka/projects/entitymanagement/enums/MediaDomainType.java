package com.janaka.projects.entitymanagement.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum MediaDomainType {
	OTHER(0), NIC(1), APPLICATION(2);

	private int code;

	private MediaDomainType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	private static final Map<Integer, MediaDomainType> LOOKUP = new HashMap<Integer, MediaDomainType>();

	static {
		for (MediaDomainType mediaDomainType : EnumSet.allOf(MediaDomainType.class)) {
			LOOKUP.put(mediaDomainType.getCode(), mediaDomainType);
		}
	}

	public static MediaDomainType fromCode(int code) {
		return LOOKUP.get(code);
	}
}
