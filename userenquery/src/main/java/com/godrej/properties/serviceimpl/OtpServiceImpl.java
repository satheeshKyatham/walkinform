package com.godrej.properties.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.DeviceRegisterDao;
import com.godrej.properties.dao.OtpDao;
import com.godrej.properties.model.OTP;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.service.OtpService;

@Service("otpService")
@Transactional
public class OtpServiceImpl implements OtpService {

	@Autowired
	OtpDao otpDao;
	
	@Autowired
	DeviceRegisterDao deviceRegisterDao ;
	
	
	@Override
	public OTP createOtp(String mobileno) {
		// TODO Auto-generated method stub
		return otpDao.createOtp(mobileno);
	}

	@Override
	public OTP getValidOtp(String mobileno, String otp) {
		// TODO Auto-generated method stub
		return otpDao.getValidOtp(mobileno,otp);
	}

	@Override
	public OTP getOtp(String mobileno) {
		// TODO Auto-generated method stub
		return otpDao.getOtp(mobileno);
	}

	@Override
	public UserMaster authenticationDevice(String deviceId) {
		// TODO Auto-generated method stub
		return otpDao.authenticationDevice(deviceId);
	}

	@Override
	public void insertDevice(String deviceId) {
		// TODO Auto-generated method stub
		deviceRegisterDao.insertDevice(deviceId);
	}

	@Override
	public OTP createOtpCountry(String countryCode, String mobileNo) {
		// TODO Auto-generated method stub
		return otpDao.createOtpCountry(countryCode,mobileNo);
	}

 

}
