package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CoApplicant;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.Token;


public interface EOIEnquiryDao {
	List<EOIData> findMobileNoExist(String mobileno);

	void createQRCode(String mobileno, String filePath, String code_numeric, String code_str);

	List<EOIData> searchUserQRCode(String code);

	void UpdateToken(Token token);

	List<EOIData> getAllData();

	public List<CoApplicant> getCoapplicantData(String enqid);
	//void save(EOIData s);
}
