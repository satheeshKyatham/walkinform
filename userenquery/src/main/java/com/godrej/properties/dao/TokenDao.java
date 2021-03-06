package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Token;

public interface TokenDao {

	Token generateToken(String mobileno, String type);

	Token generateToken(Token token);

	List<Token> getuniqeTypes();

	Token getNextType(String type, String counterNo, String lastTokenNo);

	String getDealDone(String lastTokenNo);
	String getDealStarted(String lastTokenNo);
	
	List<Token> getDisplayTV();

	List<Token> getTokenList(String tokenType, String projectId);

	List<Token> getTokenAssignList(String tokenType);

	List<Token> getTokenList(String tokenType);

	Token updateAssignStatus(String id, String assinedto);

	Token updateTokenStatus(String mobileno);

	Token updateTokenEnquiryID(String mobileno, String sfid, String tokenid);
	
	Token getTokenDetails(String tokenID);

	/* String updateReAssignToken(String tokenID, String assinedto); */
	String updateEnqSalesTab(int enqid,String email);
	String getSalesUserSFID(int enqid, String email);
	String getSalesUserEmailID(int enqid, String sfid);
	
	public Token getTokenByEnquiry(String enquirySfid);
	public int getEnquiryIDFromSFID(String enquirySfid);
	public void updateEnquiryData(int enqID,String updateData);
	public void updateContactData(int contactID,String updateData);
	public void updateClosingMangerOnOfferCreation(String enquirysfid);
}
