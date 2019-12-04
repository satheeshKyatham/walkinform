package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.ExtraCharges;

public interface EOIPaymentDtlService {
	void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl);
	
	List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid);
	
	void updateEOIForOffer(List<EOIPaymentDtl> charges);
}