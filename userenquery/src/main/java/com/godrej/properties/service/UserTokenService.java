package com.godrej.properties.service;

import com.godrej.properties.dto.UserTokenDto;

public interface UserTokenService {

	public UserTokenDto sendToken(String enquiryId,
			 String mobileNo,
			 String projectSFID,
			 String projectName,
			 String countryCode,
			 String userId);
}
