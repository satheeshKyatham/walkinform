package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CoApplicant;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.Token;



public interface EOIEnquiryService {

	List<EOIData> findMobileNoExist(String mobileno);

	List<EOIData> getAllData();
	
	void createQRCode(String mobileno, String path);

	
	List<EOIData> searchUserQRCode(String code);
	//void save(EOIData s);

	void UpdateToken(Token token);
	public List<CoApplicant> getCoapplicantData(String enqid);

}
