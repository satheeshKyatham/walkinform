package com.godrej.properties.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salesforce.gpl_cs_otp_log")
public class OTPRequestOC implements Serializable{
	
	private static final long serialVersionUID = 1L; 

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "otp_id") 
	private int id;
	  
	@Column(name = "otp")
	private String otp;
	
	@Column(name = "isactive")
	private String isactive;
	
	@Column(name = "enquiry_id")
	private String enquiry_id;
	
	@Column(name = "app_type")
	private String app_type;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getEnquiry_id() {
		return enquiry_id;
	}

	public void setEnquiry_id(String enquiry_id) {
		this.enquiry_id = enquiry_id;
	}

	public String getApp_type() {
		return app_type;
	}

	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}