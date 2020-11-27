package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.EOIPreferenceDtl;

public interface EOIPaymentDtlDao {
	void insertPaymentDtl (List<EOIPaymentDtl> pymtDtl);
	
	List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid,String whereCondition);
	
	void updateEOIForOffer(List<EOIPaymentDtl> charges);
	public List<EOIPaymentDtl> getCommonEOIPaymentEntrys(String whereCondition);
	void paymentEOIApproveReject(String whereCondition);
	void insertOnePaymentDtl(EOIPaymentDtl pymtDtl);
	
	Boolean updateEOIPayment(List<EOIPaymentDtl> eoiReq);
	Boolean inactiveEOIPayment(List<EOIPaymentDtl> eoiReq);
}