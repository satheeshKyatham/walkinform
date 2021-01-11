package com.godrej.properties.dto;

import java.sql.Timestamp;

import com.godrej.properties.common.dto.CommonDto;

public class PhaseDto  extends CommonDto{

	/**
	 * @author sathish.kyatham
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer phaseId;
	private String name;
	private String project__c;
	private Timestamp createddate;
	public Integer getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(Integer phaseId) {
		this.phaseId = phaseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProject__c() {
		return project__c;
	}
	public void setProject__c(String project__c) {
		this.project__c = project__c;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	
}
