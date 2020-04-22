package com.godrej.properties.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.godrej.properties.common.utilities.CommonValidations;


public class CommonContextDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String isActive;
	private Timestamp created;
	private Timestamp updated;
	private ResponseDto response;
	
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	public ResponseDto getResponse() {
		return response;
	}
	public void setResponse(ResponseDto response) {
		this.response = response;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	

	
}
