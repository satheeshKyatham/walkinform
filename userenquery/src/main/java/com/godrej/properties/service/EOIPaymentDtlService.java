package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.ExtraCharges;

public interface EOIPaymentDtlService {
	void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl);
	
	public List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid,String whereCondition);
	
	void updateEOIForOffer(List<EOIPaymentDtl> charges);
	public List<EOIPaymentDtl> getCommonEOIPaymentEntrys(String whereCondition);
	public void paymentEOIApproveReject(String whereCondition);
	
	String updateEOIPayment(String paymentDtlJson, String userid, String enq_sfid, String project_sfid, String username);
	String deleteEOIPayment(String paymentJson, String userid, String enq_sfid, String project_sfid);
}