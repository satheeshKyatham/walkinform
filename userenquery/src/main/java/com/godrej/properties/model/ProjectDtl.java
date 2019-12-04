package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="salesforce.propstrength__projects__c")

public class ProjectDtl implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	
	
	@Column(name = "stamp_duty__c")
	String stamp_duty__c_string;
	@Transient
	private double stamp_duty__c;
	
	
	@Column(name = "project_18_digit__c")
	private String project_18_digit__c;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "rera_registration_number__c")
	private String rera_registration_number__c;
	
	@Column(name = "gstin__c")
	private String gstin__c;
	
	@Column(name = "marketing_project_name__c")
	private String marketing_project_name__c;
	
	@Column(name = "region__c")
	private String region__c;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getStamp_duty__c() {
		
		
		
		return stamp_duty__c;
	}

	public void setStamp_duty__c(double stamp_duty__c) {
		this.stamp_duty__c = stamp_duty__c;
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

	public String getRera_registration_number__c() {
		return rera_registration_number__c;
	}

	public void setRera_registration_number__c(String rera_registration_number__c) {
		this.rera_registration_number__c = rera_registration_number__c;
	}

	public String getGstin__c() {
		return gstin__c;
	}

	public void setGstin__c(String gstin__c) {
		this.gstin__c = gstin__c;
	}

	public String getMarketing_project_name__c() {
		return marketing_project_name__c;
	}

	public void setMarketing_project_name__c(String marketing_project_name__c) {
		this.marketing_project_name__c = marketing_project_name__c;
	}

	public String getRegion__c() {
		return region__c;
	}

	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStamp_duty__c_string() {
		return stamp_duty__c_string;
	}

	public void setStamp_duty__c_string(String stamp_duty__c_string) {
		this.stamp_duty__c_string = stamp_duty__c_string;
		if (stamp_duty__c_string != null) {
			stamp_duty__c = Double.valueOf(stamp_duty__c_string);
		} else {
			stamp_duty__c = 0;
		}
	}
}