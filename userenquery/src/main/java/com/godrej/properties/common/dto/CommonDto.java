package com.godrej.properties.common.dto;

import java.io.Serializable;

import com.godrej.properties.common.utilities.CommonValidations;

public class CommonDto implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String isActive;
	/*private String synchronised;*/
	private String sfid;
	private String message;
	private boolean hasError;
	
	public String getIsActive() {
		if(CommonValidations.isEmpty(isActive)){
			return "N";
		}
		return isActive;
	}
	
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	/*public String getSynchronised() {
		if(CommonValidations.isEmpty(isActive)){
			return "N";
		}
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}*/

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}
	
}
