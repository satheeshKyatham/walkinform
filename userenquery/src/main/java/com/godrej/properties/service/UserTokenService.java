package com.godrej.properties.service;

public interface UserTokenService {

	public String sendToken(String enquiryId,
			 String mobileNo,
			 String projectSFID,
			 String projectName,
			 String countryCode,
			 String userId);
}
