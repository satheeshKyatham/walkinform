package com.godrej.properties.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CommonModel implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sfid;
	
	/*private String isActive;*/
	/*@Column(name="isactive")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}*/
	/*private String synchronised;
	
	
	
	@Column(name="synchronised")
	public String getSynchronised() {
		return synchronised;
	}
	public void setSynchronised(String synchronised) {
		this.synchronised = synchronised;
	}*/
	

	@Column(name="sfid",insertable=false,updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	
}
