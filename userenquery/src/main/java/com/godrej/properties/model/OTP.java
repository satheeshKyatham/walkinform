package com.godrej.properties.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 

@Entity
@Table(name="salesforce.nv_otp_log")
public class OTP {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="otp_id") private String  otp_id ;
	@Column(name="otp") private String  otp ;
	@Column(name="expirydate") private Timestamp  expirydate ;
	@Column(name="createddate") private Timestamp  createddate ;
	@Column(name="isactive") private String  isactive ;
	@Column(name="mobileno") private String  mobileno ;
	@Column(name="app_type") private String  app_type ;
	@Column(name="countrycode") private String  countrycode ;
	
	public String getOtp_id() {
		return otp_id;
	}
	public void setOtp_id(String otp_id) {
		this.otp_id = otp_id;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Timestamp getExpirydate() {
		return expirydate;
	}
	public void setExpirydate(Timestamp expirydate) {
		this.expirydate = expirydate;
	}
	public Timestamp getCreateddate() {
		return createddate;
	}
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	
	
	 
}
