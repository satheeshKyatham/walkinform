package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.GeneratePayment;

public interface GeneratePaymentService {
	void insertPaymentDtl(List<GeneratePayment> pymtDtl);
	public List<GeneratePayment> getPaymentRecord(String enqSfid, String projectid);
	public GeneratePayment getCCPaymentData(int id);
	public String createCCGatewayRequest(GeneratePayment payment);
}