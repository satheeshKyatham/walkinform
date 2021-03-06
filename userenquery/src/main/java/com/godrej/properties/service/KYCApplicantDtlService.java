package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.EOIDataVW;
import com.godrej.properties.model.KYCApplicantDtl;

public interface KYCApplicantDtlService {
	public List<KYCApplicantDtl> getApplicantData(String enqName, String contactSFID);
	public List<EOIData> getKYCData(String userid, String projectid);
	public EOIData getKYCStatus(String enquiryName, String projectid);
	public List<EOIDataVW> getKYCDataVW(String userid, String projectid);
}
