package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentDtl;

public interface PaymentDtlDao {
	void insertPaymentDtl (List<PaymentDtl> pymtDtl);
	List<PaymentDtl> getPrePaymentData(String enq);
}
