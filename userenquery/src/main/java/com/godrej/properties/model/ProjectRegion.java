package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.propstrength__projects__c")
public class ProjectRegion implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
	  
	@Column(name = "project_18_digit__c")
	private String project_18_digit__c;

	@Column(name = "name")
	private String name;

	@Column(name = "launch__c")
	private String launch__c;

	@Column(name = "region__c")
	private String region__c;
	
	@Column(name = "marketing_project_name__c")
	private String marketing_project_name__c;
	
	
	@Column(name = "sfid")
	private String sfid;
	
	@Column(name = "propstrength__project_code__c")
	private String propstrength__project_code__c;
	
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

	public String getLaunch__c() {
		return launch__c;
	}

	public void setLaunch__c(String launch__c) {
		this.launch__c = launch__c;
	}

	public String getRegion__c() {
		return region__c;
	}

	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMarketing_project_name__c() {
		return marketing_project_name__c;
	}

	public void setMarketing_project_name__c(String marketing_project_name__c) {
		this.marketing_project_name__c = marketing_project_name__c;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getPropstrength__project_code__c() {
		return propstrength__project_code__c;
	}

	public void setPropstrength__project_code__c(String propstrength__project_code__c) {
		this.propstrength__project_code__c = propstrength__project_code__c;
	}
}