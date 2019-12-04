package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="salesforce.nv_device_register")
public class DeviceRegister {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="nv_device_info_id") private int  nv_device_info_id ;
	@Column(name="device_id") private String  device_id ;
	@Column(name="isactive") private String  isactive ;
	@Column(name="projectName") private String  projectName ;
	@Column(name="projectid") private String  projectid ;
	@Column(name="region_id") private String  region_id ;
	@Column(name="region_name") private String  region_name ;
	public int getNv_device_info_id() {
		return nv_device_info_id;
	}
	public void setNv_device_info_id(int nv_device_info_id) {
		this.nv_device_info_id = nv_device_info_id;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 	 
}
