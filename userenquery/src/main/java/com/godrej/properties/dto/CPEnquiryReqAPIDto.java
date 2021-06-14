package com.godrej.properties.dto;


/**
 Created by balram for CP source protection request 
*/
public class CPEnquiryReqAPIDto {
	public String projectsfid;
	public String countrycode;
	public String mobileno;
	public String brokerId;
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	public String getProjectsfid() {
		return projectsfid;
	}
	public void setProjectsfid(String projectsfid) {
		this.projectsfid = projectsfid;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

}
