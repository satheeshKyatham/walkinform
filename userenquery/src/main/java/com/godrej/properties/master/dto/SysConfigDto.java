package com.godrej.properties.master.dto;

import java.io.Serializable;

/**
 * 
 * @author Vivek Birdi
 *
 */
public class SysConfigDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sysConfigId;
	private String value;
	private String name;
	private String isActive;
	
	public Long getSysConfigId() {
		return sysConfigId;
	}
	public void setSysConfigId(Long sysConfigId) {
		this.sysConfigId = sysConfigId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
