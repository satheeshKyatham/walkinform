package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.GeneratePayment;

public interface GeneratePaymentDao {
	void insertPaymentDtl (List<GeneratePayment> pymtDtl);
	List<GeneratePayment> getPaymentRecord(String enqSfid, String projectid);
	public GeneratePayment getCCPayment(int id);
}
