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
@Table(name = "salesforce.propstrength__application_booking__c") 
public class ApplicationDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="id") private  int id ;
	
	@Column(name="application_booking_id_18__c") private String	application_booking_id_18__c;
	@Column(name="propstrength__project__c") private String	propstrength__project__c;
	@Column(name="propstrength__primary_applicant_name__c") private String	propstrength__primary_applicant_name__c;
	@Column(name="booking_source__c") private String	booking_source__c;
	@Column(name="propstrength__broker_name__c") private String	propstrength__broker_name__c;
	@Column(name="propstrength__property_name__c") private String	propstrength__property_name__c;
	@Column(name="propstrength__tower__c") private String	propstrength__tower__c;
	@Column(name="propstrength__type__c") private String	propstrength__type__c;
	@Column(name="enquiry18digit__c") private String enquiry18digit__c;
	@Column(name="createddate") private Date createddate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApplication_booking_id_18__c() {
		return application_booking_id_18__c;
	}
	public void setApplication_booking_id_18__c(String application_booking_id_18__c) {
		this.application_booking_id_18__c = application_booking_id_18__c;
	}
	public String getPropstrength__project__c() {
		return propstrength__project__c;
	}
	public void setPropstrength__project__c(String propstrength__project__c) {
		this.propstrength__project__c = propstrength__project__c;
	}
	public String getPropstrength__primary_applicant_name__c() {
		return propstrength__primary_applicant_name__c;
	}
	public void setPropstrength__primary_applicant_name__c(String propstrength__primary_applicant_name__c) {
		this.propstrength__primary_applicant_name__c = propstrength__primary_applicant_name__c;
	}
	public String getBooking_source__c() {
		return booking_source__c;
	}
	public void setBooking_source__c(String booking_source__c) {
		this.booking_source__c = booking_source__c;
	}
	public String getPropstrength__broker_name__c() {
		return propstrength__broker_name__c;
	}
	public void setPropstrength__broker_name__c(String propstrength__broker_name__c) {
		this.propstrength__broker_name__c = propstrength__broker_name__c;
	}
	public String getPropstrength__property_name__c() {
		return propstrength__property_name__c;
	}
	public void setPropstrength__property_name__c(String propstrength__property_name__c) {
		this.propstrength__property_name__c = propstrength__property_name__c;
	}
	public String getPropstrength__tower__c() {
		return propstrength__tower__c;
	}
	public void setPropstrength__tower__c(String propstrength__tower__c) {
		this.propstrength__tower__c = propstrength__tower__c;
	}
	public String getPropstrength__type__c() {
		return propstrength__type__c;
	}
	public void setPropstrength__type__c(String propstrength__type__c) {
		this.propstrength__type__c = propstrength__type__c;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEnquiry18digit__c() {
		return enquiry18digit__c;
	}
	public void setEnquiry18digit__c(String enquiry18digit__c) {
		this.enquiry18digit__c = enquiry18digit__c;
	}
	public Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}
}