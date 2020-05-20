package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.OTPAdminLog;

public interface OTPAdminLogDao {
	boolean insertAdminOTPLog (List<OTPAdminLog> log);
}