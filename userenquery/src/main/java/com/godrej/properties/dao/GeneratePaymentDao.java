package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.GeneratePayment;

public interface GeneratePaymentDao {
	Boolean insertPaymentDtl (List<GeneratePayment> pymtDtl);
	List<GeneratePayment> getPaymentRecord(String enqSfid, String projectSFID);
	public GeneratePayment getCCPaymentData(int id);
}