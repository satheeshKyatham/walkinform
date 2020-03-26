package com.godrej.properties.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.controller.GenerateQRCode;
import com.godrej.properties.dao.EOIEnquiryDao;
import com.godrej.properties.model.CoApplicant;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.VWEOILimitAmount;
import com.godrej.properties.service.EOIEnquiryService;
import com.godrej.properties.util.UniqeGenerator;
import com.google.gson.JsonElement;
import com.google.zxing.WriterException;




@Service("userEOIService")
@Transactional
public class EOIEnquiryServiceImpl implements EOIEnquiryService {

	@Autowired
	EOIEnquiryDao userEOIDao;
	
	@Override
	public List<EOIData> findMobileNoExist(String mobileno) {
		// TODO Auto-generated method stub
		return userEOIDao.findMobileNoExist(mobileno);
	}

	@Override
	public void createQRCode(String mobileno,String path) {
		
		try {
			String filePath =path+File.separator +mobileno+".png";
			int size = 125;
			String fileType = "png";
			File qrFile = new File(filePath);
			String code_numeric=UniqeGenerator.createRandomInteger();
			String code_str=UniqeGenerator.randomString(15);
			GenerateQRCode.createQRImage(qrFile, code_numeric, size, fileType);
			userEOIDao.createQRCode(mobileno,mobileno+".png",code_numeric,code_str);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<EOIData> searchUserQRCode(String code) {
		// TODO Auto-generated method stub
		return userEOIDao.searchUserQRCode(code);
	}

	@Override
	public void UpdateToken(Token token) {
		 
		userEOIDao.UpdateToken(token);
	}

	@Override
	public List<EOIData> getAllData() {
		// TODO Auto-generated method stub
		return userEOIDao.getAllData();
	}

	@Override
	public List<CoApplicant> getCoapplicantData(String enqid) {
		// TODO Auto-generated method stub
		return userEOIDao.getCoapplicantData(enqid);
	}

	@Override
	public String getEOITokenType(String enqsfid) {
		VWEOILimitAmount eoiAmount = userEOIDao.getEOITokenType(enqsfid);
		String tokenType="";
		if(eoiAmount!=null && (eoiAmount.getEoi_sum_amount()>=eoiAmount.getEoi_limit_amount()))
		{
			tokenType="G";
		}
		else if(eoiAmount!=null && (eoiAmount.getEoi_sum_amount()<eoiAmount.getEoi_limit_amount()))
		{
			tokenType="E";
		}
		else
			tokenType="W";
		return tokenType;
	}

	@Override
	public List<EOIData> findMobileNoExistEOIForm(String mobileno, String projectid,String enqsfid) {
			return userEOIDao.findMobileNoExistEOIForm(mobileno,projectid,enqsfid);
		}
		

	@Override
	public List<EOIData> findMobileNoExist(String mobileno, String project_sfid) {
		return userEOIDao.findMobileNoExist(mobileno,project_sfid);
		
	}

//	@Override
//	public void save(EOIData s) {
//	 
//		userContactDao.save(s);
//	}

}
