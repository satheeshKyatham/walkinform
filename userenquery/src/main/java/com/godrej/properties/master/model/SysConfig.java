package com.godrej.properties.master.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Vivek Birdi
 *
 */

@Entity
@Table(name = "salesforce.ad_sysconfig")
public class SysConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5281616534604888786L;
	
	private Long sysConfigId;
	private String value;
	private String name;
	private String isActive;
	private String recordId;
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ad_sysconfig_id")
	public Long getSysConfigId() {
		return sysConfigId;
	}
	public void setSysConfigId(Long sysConfigId) {
		this.sysConfigId = sysConfigId;
	}
	@Column(name = "value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "isactive")
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	@Column(name  ="record_id")
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
}
