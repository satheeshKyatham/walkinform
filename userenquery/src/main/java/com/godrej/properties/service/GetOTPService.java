package com.godrej.properties.service;

public interface GetOTPService {
	String adminOTP(String projectsfid, String loggedinuserid, String otprequesterid, String mobileno);
}