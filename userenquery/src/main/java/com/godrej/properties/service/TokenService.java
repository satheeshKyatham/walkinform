package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PriceSheet;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.VWTokenScreen;
import com.godrej.properties.model.VW_Token;

public interface TokenService {
	Token generateToken(String mobileno, String type);

	Token generateToken(Token token);

	List<Token> getuniqeTypes();

	Token getNextType(String type, String counterNo, String lastTokenNo);

	List<Token> getDisplayTV();

	String checkPriceDetails(PriceSheet pricesheet);

	List<VW_Token> getTokenList(String tokenType, String projectId, String inputDate,String toDate);

	List<VW_Token> getTokenAssignList(String tokenType, String projectid, String inputDate,String toDate);

	List<Token> getTokenList(String tokenType);

	List<Token> getTokenAssignList(String tokenType);

	Token updateAssignStatus(String id, String assinedto);

	Token updateTokenStatus(String mobileno);

	Token updateTokenEnquiryID(String mobileno, String sfid, String tokenid);

	String getDealDone(String tokenid);
	String getDealStarted(String lastTokenNo);
	
	Token getTokenDetails(String tokenID);
	String updateEnqSalesTab(int enqid,String email);
	String getSalesUserSFID(int enqid, String email);
	String getSalesUserEmailID(int enqid, String sfid);
	public Token getTokenByEnquiry(String enquirySfid);
	
	List<VWTokenScreen> getUpcomingToken(String tokenType, String projectId, String inputDate,String toDate);
	
	List<VWTokenScreen> getAssignedList(String tokenType, String projectid, String inputDate,String toDate);
}