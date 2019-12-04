package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.nv_projects_c")
public class ProjectLaunch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id") private String  id;
	@Column(name="project_18_digit__c") private String  project_18_digit__c;
	@Column(name="name") private String  name;
	@Column(name="propstrength__project_code__c") private String  propstrength__project_code__c;
	@Column(name="isActive") private String  isActive;
	@Column(name="region__c") private String  regionname;
	@Column(name="salesmanager_sfid") private String  salesmanager_sfid;
	
	@Column(name="mid") private String  mid;
	@Column(name="access_key") private String  access_key;
	@Column(name="working_key") private String  working_key;
	
	public String getSalesmanager_sfid() {
		return salesmanager_sfid;
	}
	public void setSalesmanager_sfid(String salesmanager_sfid) {
		this.salesmanager_sfid = salesmanager_sfid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_18_digit__c() {
		return project_18_digit__c;
	}
	public void setProject_18_digit__c(String project_18_digit__c) {
		this.project_18_digit__c = project_18_digit__c;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPropstrength__project_code__c() {
		return propstrength__project_code__c;
	}
	public void setPropstrength__project_code__c(String propstrength__project_code__c) {
		this.propstrength__project_code__c = propstrength__project_code__c;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getAccess_key() {
		return access_key;
	}
	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}
	public String getWorking_key() {
		return working_key;
	}
	public void setWorking_key(String working_key) {
		this.working_key = working_key;
	}
	 

}
