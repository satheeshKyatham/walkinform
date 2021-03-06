package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "salesforce.propstrength__request__c")
public class EnquiryRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	


	}
