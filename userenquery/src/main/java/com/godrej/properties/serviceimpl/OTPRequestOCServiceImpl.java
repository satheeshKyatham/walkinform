package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OTPRequestOCDao;
import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.OTPRequestOC;
import com.godrej.properties.service.OTPRequestOCService;

@Service("oTPRequestOCService")
@Transactional
public class OTPRequestOCServiceImpl implements OTPRequestOCService{
	
	@Autowired
	private OTPRequestOCDao dao;
	
	@Autowired
	OTPRequestOCDao oTPRequestOCDao;
	
	
	@Override
	public void insertOTPRqst(OTPRequestOC action) {
		dao.insertOTPRqst(action);		
	}
	
	@Override
	public void updatePreviousOtp(OTPRequestOC updateOTP) {
		dao.updatePreviousOtp(updateOTP);		
	}
	
	@Override
	public OTPRequestOC validateOTP(String OTPValidate, String herokuEnqId) {
		// TODO Auto-generated method stub
		return dao.validateOTP(OTPValidate, herokuEnqId);
	}
}