package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.GeneratePayment;


public interface GeneratePaymentService {
	void updatePaymentReq(List<GeneratePayment> payReq);
	Boolean insertPaymentDtl(List<GeneratePayment> pymtDtl);
	public List<GeneratePayment> getPaymentRecord(String enqSfid, String projectid);
	public GeneratePayment getCCPaymentData(int id);
	public String createCCGatewayRequest(GeneratePayment payment);
	//public CCAvenueGatewayRequest insertCCAvenueGatewayRequest(CCAvenueGatewayRequest gatewayRequest);
	public List<GeneratePayment> getPaymentDetails(String enqSfid, String projectSFID);
}