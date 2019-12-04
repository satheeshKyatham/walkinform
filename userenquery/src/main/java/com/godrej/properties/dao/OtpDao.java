package com.godrej.properties.dao;

import com.godrej.properties.model.OTP;
import com.godrej.properties.model.UserMaster;

public interface OtpDao {
	OTP createOtp(String mobileNo);
	OTP createOtpCountry(String countryCode,String mobileNo);
	OTP getValidOtp(String mobileno, String otp);

	OTP getOtp(String mobileno);

	UserMaster authenticationDevice(String deviceId); 
}
