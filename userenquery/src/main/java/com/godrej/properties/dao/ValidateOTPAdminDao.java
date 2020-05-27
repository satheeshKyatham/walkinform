package com.godrej.properties.dao;

public interface ValidateOTPAdminDao {
	boolean validateAdmin (String loggedinuserid);
	boolean validateRequester (String otprequesterid);
}