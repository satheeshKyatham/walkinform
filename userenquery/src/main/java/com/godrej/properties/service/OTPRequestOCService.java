package com.godrej.properties.service;

import com.godrej.properties.model.OTPRequestOC;

public interface OTPRequestOCService {
	void insertOTPRqst (OTPRequestOC action);

	OTPRequestOC validateOTP(String oTPValidate, String herokuEnqId);

	void updatePreviousOtp(OTPRequestOC updateOTP);
}
