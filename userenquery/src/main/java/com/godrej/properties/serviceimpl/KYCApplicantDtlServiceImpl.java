package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ApplicantDtlDao;
import com.godrej.properties.dao.KYCApplicantDtlDao;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.KYCApplicantDtl;
import com.godrej.properties.service.ApplicantDtlService;
import com.godrej.properties.service.KYCApplicantDtlService;

@Service("kYCapplicantDtlService")
@Transactional
public class KYCApplicantDtlServiceImpl implements KYCApplicantDtlService{
	@Autowired
	KYCApplicantDtlDao kYCapplicantDtlDao;
	
	@Override
	public List<KYCApplicantDtl> getApplicantData(String enqName, String contactSFID) {
		return kYCapplicantDtlDao.getApplicantData(enqName, contactSFID);
	}

	@Override
	public List<EOIData> getKYCData(String userid, String projectid) {
		return kYCapplicantDtlDao.getKYCData(userid, projectid);
	}
	@Override
	public EOIData getKYCStatus(String enquiryName, String projectid) {
		return kYCapplicantDtlDao.getKYCStatus(enquiryName, projectid);
	}
}