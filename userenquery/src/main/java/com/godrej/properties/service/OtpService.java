package com.godrej.properties.service;

import com.godrej.properties.model.OTP;
import com.godrej.properties.model.UserMaster;
import com.google.gson.JsonElement;

public interface OtpService {

	OTP createOtp(String mobileNo);
	OTP createOtpCountry(String countryCode, String mobileNo);

	OTP getValidOtp(String mobileno, String otp); 
	
	OTP getOtp(String mobileno);

	UserMaster authenticationDevice(String deviceId);

	void insertDevice(String deviceId); 

}
