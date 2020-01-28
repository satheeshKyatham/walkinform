package com.godrej.properties.serviceimpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.kyc.util.StringEncDec;
import com.godrej.properties.dto.UserTokenDto;
import com.godrej.properties.model.Token;
import com.godrej.properties.service.TokenService;
import com.godrej.properties.service.UserTokenService;
import com.godrej.properties.util.SendSMS;

@Service
public class UserTokenServiceImpl implements UserTokenService{
	
 	@Autowired
	private	TokenService tokenService; 

 	@SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserTokenDto sendToken(String enquiryId, String mobileNo, String projectSFID, String projectName,
			String countryCode, String userId) {

		Token token = new Token();
		token.setCreated(new Timestamp(new Date().getTime()));
		
		token.setMobileno(mobileNo);
		token.setProjectname(projectSFID);
		token.setEnquiry_18(String.valueOf(enquiryId));
		token.setType("W");
		token.setUniqe_no("999999");
		token.setUniqe_str("999999");
		token.setAmount("");
		token.setDocNo("");
		token.setIsKYCFilled("N");
		token.setIsactive("Y");
		token.setCountrycode(countryCode);
		token=	tokenService.generateToken(token);
	
		String encStr = StringEncDec.encrypt(mobileNo);
		token.setEncStr(encStr);
		
		
		String tokenId = String.valueOf(token.getNv_token_id());
		tokenService.updateAssignStatus(tokenId, userId);
		UserTokenDto userToken =  new UserTokenDto();
		userToken.setTokenId(tokenId);
		userToken.setTokenNo(token.getType()+token.getQueue());
		return userToken;
	}

	public void sendTokenSMS(String mobile ,String token,String projName) throws UnsupportedEncodingException {//,String cpflag,String cpName
		StringBuilder msg=  new StringBuilder();
		msg.append("Thank you for your interest in ")
		.append(projName)
		.append(". Your token no is ")
		.append(token)
		.append(". Regards, Godrej Properties.");
	 		 	
	 	String message = URLEncoder.encode(msg.toString(), "UTF-8");
		SendSMS.SMSSend(mobile,message);
	}

}
