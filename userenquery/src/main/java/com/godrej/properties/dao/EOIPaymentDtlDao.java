package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.ExtraCharges;

public interface EOIPaymentDtlDao {
	void insertPaymentDtl (List<EOIPaymentDtl> pymtDtl);
	
	List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid);
	
	void updateEOIForOffer(List<EOIPaymentDtl> charges);
}
