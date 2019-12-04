package com.godrej.properties.master.dto;

import com.godrej.properties.common.dto.CommonDto;

public class ReferenceListDto extends CommonDto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String referenceListId;
	private String name;
	private String code;
	private String description;
	private String isActive;
	
	private ReferenceDto reference;

	public String getReferenceListId() {
		return referenceListId;
	}

	public void setReferenceListId(String referenceListId) {
		this.referenceListId = referenceListId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReferenceDto getReference() {
		return reference;
	}

	public void setReference(ReferenceDto reference) {
		this.reference = reference;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	
}
