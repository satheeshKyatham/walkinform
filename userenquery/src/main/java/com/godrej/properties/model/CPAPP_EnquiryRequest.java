package com.godrej.properties.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__request__c")
public class CPAPP_EnquiryRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")private int id;
 
	@Column(name = "sfid")
	private String sfid;

	@Column(name = "Walk_In_Source__c")
	private String Walk_In_Source__c;
	
	@Column(name = "Rating__c")
	private String Rating__c;

	@Column(name = "PropStrength__Broker_Account__c")
	private String PropStrength__Broker_Account__c;
	
	@Column(name = "Date_of_Site_Visit__c")
	private Date Date_of_Site_Visit__c;
	

	@Column(name = "propstrength__primary_contact__c")
	private String propstrength__primary_contact__c;

	@Column(name = "propstrength__request_source__c")
	private String propstrength__request_source__c;
	
	@Column(name = "propstrength__enquiry_type__c")
	private String propstrength__enquiry_type__c;

	@Column(name = "PropStrength__Request_Status__c")
	private String PropStrength__Request_Status__c;
	
	@Column(name = "propstrength__project__c")
	private String propstrength__project__c;
	
	@Column(name = "external_contact_id__c")
	private double external_contact_id__c;
	
	
	public double getExternal_contact_id__c() {
		return external_contact_id__c;
	}

	public void setExternal_contact_id__c(double external_contact_id__c) {
		this.external_contact_id__c = external_contact_id__c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getWalk_In_Source__c() {
		return Walk_In_Source__c;
	}

	public void setWalk_In_Source__c(String walk_In_Source__c) {
		Walk_In_Source__c = walk_In_Source__c;
	}

	public String getRating__c() {
		return Rating__c;
	}

	public void setRating__c(String rating__c) {
		Rating__c = rating__c;
	}

	public String getPropStrength__Broker_Account__c() {
		return PropStrength__Broker_Account__c;
	}

	public void setPropStrength__Broker_Account__c(String propStrength__Broker_Account__c) {
		PropStrength__Broker_Account__c = propStrength__Broker_Account__c;
	}

	public Date getDate_of_Site_Visit__c() {
		return Date_of_Site_Visit__c;
	}

	public void setDate_of_Site_Visit__c(Date date_of_Site_Visit__c) {
		Date_of_Site_Visit__c = date_of_Site_Visit__c;
	}

	public String getPropstrength__primary_contact__c() {
		return propstrength__primary_contact__c;
	}

	public void setPropstrength__primary_contact__c(String propstrength__primary_contact__c) {
		this.propstrength__primary_contact__c = propstrength__primary_contact__c;
	}

	public String getPropstrength__request_source__c() {
		return propstrength__request_source__c;
	}

	public void setPropstrength__request_source__c(String propstrength__request_source__c) {
		this.propstrength__request_source__c = propstrength__request_source__c;
	}

	public String getPropstrength__enquiry_type__c() {
		return propstrength__enquiry_type__c;
	}

	public void setPropstrength__enquiry_type__c(String propstrength__enquiry_type__c) {
		this.propstrength__enquiry_type__c = propstrength__enquiry_type__c;
	}

	public String getPropStrength__Request_Status__c() {
		return PropStrength__Request_Status__c;
	}

	public void setPropStrength__Request_Status__c(String propStrength__Request_Status__c) {
		PropStrength__Request_Status__c = propStrength__Request_Status__c;
	}

	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}

	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	}
