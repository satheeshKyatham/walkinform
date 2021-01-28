package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.godrej.properties.common.model.CommonModel;

/**
 * @author sathish.kyatham
 * 
 */
@Entity
@Table(name="salesforce.project_phase__c")
public class Phase extends CommonModel{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer phaseid;
	@Column(name="createddate")
	private Timestamp createddate;
	@Column(name="isdeleted")
	private boolean isdeleted;
	@Column(name="name")
	private String name;
	@Column(name="project__c")
	private String project__c;
	public Integer getPhaseid() {
		return phaseid;
	}
	public void setPhaseid(Integer phaseid) {
		this.phaseid = phaseid;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
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
	
	

}
