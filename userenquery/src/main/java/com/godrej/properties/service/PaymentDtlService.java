package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentDtl;

public interface PaymentDtlService {
	void insertPaymentDtl(List<PaymentDtl> pymtDtl);
	List<PaymentDtl> getPrePaymentData(String enq);
	
}