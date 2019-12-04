package com.godrej.properties.dao;

import com.godrej.properties.model.OTPRequestOC;

public interface OTPRequestOCDao {
	void insertOTPRqst (OTPRequestOC action);
	
	OTPRequestOC validateOTP(String OTPValidate, String herokuEnqId);

	void updatePreviousOtp(OTPRequestOC updateOTP);
}
