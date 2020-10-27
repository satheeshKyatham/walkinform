package com.godrej.properties.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.propstrength__offer__c")
public class EnqAndProjectDtl implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id") private int id;
	@Column(name="closing_manager_name__c") private String closing_manager_name__c;
	@Column(name="verticle__c") private String verticle__c;
	@Column(name="walk_in_source__c") private String walk_in_source__c;
	@Column(name="propstrength__enquiry_type__c") private String propstrength__enquiry_type__c;
	@Column(name="propstrength__primary_contact__c") private String propstrength__primary_contact__c;
	@Column(name="broker_name") private String broker_name;
	@Column(name="broker_mobile") private String broker_mobile;
	@Column(name="marketing_project_name__c") private String marketing_project_name__c;
	@Column(name="date_of_eoi__c") private Date date_of_eoi__c;
	@Column(name="sourcing_manager_name__c") private String sourcing_manager_name__c;
	@Column(name="enq_name") private String enq_name;	
	@Column(name="region__c") private String region__c;
	@Column(name="projectNameWithoutCity") private String projectNameWithoutCity;
	
	@Column(name="project_sfid") private String project_sfid;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClosing_manager_name__c() {
		return closing_manager_name__c;
	}
	public void setClosing_manager_name__c(String closing_manager_name__c) {
		this.closing_manager_name__c = closing_manager_name__c;
	}
	public String getVerticle__c() {
		return verticle__c;
	}
	public void setVerticle__c(String verticle__c) {
		this.verticle__c = verticle__c;
	}
	public String getWalk_in_source__c() {
		return walk_in_source__c;
	}
	public void setWalk_in_source__c(String walk_in_source__c) {
		this.walk_in_source__c = walk_in_source__c;
	}
	public String getPropstrength__enquiry_type__c() {
		return propstrength__enquiry_type__c;
	}
	public void setPropstrength__enquiry_type__c(String propstrength__enquiry_type__c) {
		this.propstrength__enquiry_type__c = propstrength__enquiry_type__c;
	}
	public String getPropstrength__primary_contact__c() {
		return propstrength__primary_contact__c;
	}
	public void setPropstrength__primary_contact__c(String propstrength__primary_contact__c) {
		this.propstrength__primary_contact__c = propstrength__primary_contact__c;
	}
	public String getBroker_name() {
		return broker_name;
	}
	public void setBroker_name(String broker_name) {
		this.broker_name = broker_name;
	}
	public String getBroker_mobile() {
		return broker_mobile;
	}
	public void setBroker_mobile(String broker_mobile) {
		this.broker_mobile = broker_mobile;
	}
	public String getMarketing_project_name__c() {
		return marketing_project_name__c;
	}
	public void setMarketing_project_name__c(String marketing_project_name__c) {
		this.marketing_project_name__c = marketing_project_name__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getDate_of_eoi__c() {
		return date_of_eoi__c;
	}
	public void setDate_of_eoi__c(Date date_of_eoi__c) {
		this.date_of_eoi__c = date_of_eoi__c;
	}
	public String getSourcing_manager_name__c() {
		return sourcing_manager_name__c;
	}
	public void setSourcing_manager_name__c(String sourcing_manager_name__c) {
		this.sourcing_manager_name__c = sourcing_manager_name__c;
	}
	public String getEnq_name() {
		return enq_name;
	}
	public void setEnq_name(String enq_name) {
		this.enq_name = enq_name;
	}
	public String getRegion__c() {
		return region__c;
	}
	public void setRegion__c(String region__c) {
		this.region__c = region__c;
	}
	public String getProjectNameWithoutCity() {
		return projectNameWithoutCity;
	}
	public void setProjectNameWithoutCity(String projectNameWithoutCity) {
		this.projectNameWithoutCity = projectNameWithoutCity;
	}
	public String getProject_sfid() {
		return project_sfid;
	}
	public void setProject_sfid(String project_sfid) {
		this.project_sfid = project_sfid;
	}
}