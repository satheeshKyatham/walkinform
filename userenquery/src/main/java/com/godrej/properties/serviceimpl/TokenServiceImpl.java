package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PriceSheetDao;
import com.godrej.properties.dao.TokenDao;
import com.godrej.properties.dao.VW_TokenDao;
import com.godrej.properties.model.PriceSheet;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.VW_Token;
import com.godrej.properties.service.TokenService;

@Service("tokenService")
@Transactional
public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenDao tokenDao;
	
	@Autowired
	VW_TokenDao vw_tokenDao;
	
	@Autowired
	PriceSheetDao priceDao;

	@Override
	public Token generateToken(String mobileno, String type) {
		// TODO Auto-generated method stub
		return tokenDao.generateToken(mobileno,type);
	}

	@Override
	public Token generateToken(Token token) {
		// TODO Auto-generated method stub
		return tokenDao.generateToken(token);
	}

	@Override
	public String checkPriceDetails(PriceSheet pricesheet) {
		// TODO Auto-generated method stub
		return priceDao.checkPriceDetails(pricesheet);
	}
	
	@Override
	public Token getNextType(String type,String counterNo,String lastTokenNo) {
		return  tokenDao.getNextType(type,counterNo,lastTokenNo);
	}
	
	@Override
	public List<Token> getDisplayTV(){
		return  tokenDao.getDisplayTV();
	}
	 
/*	@Override
	public OTP getValidOtp(String mobileno, String otp) {
		// TODO Auto-generated method stub
		return otpDao.getValidOtp(mobileno,otp);
	}*/
 
 	@Override
	public List<Token> getuniqeTypes() {
		return  tokenDao.getuniqeTypes();
	}

	@Override
	public List<VW_Token> getTokenList(String tokenType,String projectId,String inputDate,String toDate) {
		// TODO Auto-generated method stub
		return vw_tokenDao.getTokenList(tokenType,projectId,inputDate,toDate);
	}
	
	@Override
	public List<VW_Token> getTokenAssignList(String tokenType,String projectid,String inputDate,String toDate) {
		// TODO Auto-generated method stub
		return vw_tokenDao.getTokenAssignList(tokenType,projectid,inputDate,toDate);
	}


	@Override
	public List<Token> getTokenList(String tokenType) {
		// TODO Auto-generated method stub
		return tokenDao.getTokenList(tokenType);
	}
	
	@Override
	public List<Token> getTokenAssignList(String tokenType) {
		// TODO Auto-generated method stub
		return tokenDao.getTokenAssignList(tokenType);
	}

	@Override
	public Token updateAssignStatus(String id,String assinedto) {
		// TODO Auto-generated method stub
		return tokenDao.updateAssignStatus(id,assinedto);
	}

	@Override
	public Token updateTokenStatus(String mobileno) {
		// TODO Auto-generated method stub
		return tokenDao.updateTokenStatus(mobileno);
	}

	@Override
	public Token updateTokenEnquiryID(String mobileno, String sfid,String tokenid) {
		// TODO Auto-generated method stub
		return tokenDao.updateTokenEnquiryID(mobileno, sfid,tokenid);
	}


	@Override
	public String getDealDone(String tokenid) {
		// TODO Auto-generated method stub
		return tokenDao.getDealDone(tokenid);
	}

	@Override
	public String getDealStarted(String lastTokenNo) {
		// TODO Auto-generated method stub
		return tokenDao.getDealStarted(lastTokenNo);
	}

	@Override
	public Token getTokenDetails(String tokenID) {
		// TODO Auto-generated method stub
		return tokenDao.getTokenDetails(tokenID);
	}

	@Override
	public String updateEnqSalesTab(int enqid, String email) {
		// TODO Auto-generated method stub
		return tokenDao.updateEnqSalesTab(enqid, email);
	}

	@Override
	public String getSalesUserSFID(int enqid, String email) {
		// TODO Auto-generated method stub
		return tokenDao.getSalesUserSFID(enqid,email);
	}
}
