package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.RefundInitiate;

public interface RefundInitiateService {
	
	public RefundInitiate insertRefundInitiateRequest(RefundInitiate refIn);
	public List<RefundInitiate> getRefundInitiatedData(String whereCodnition);
	public String updateEOIRefundEnty(String enquiry_Sfid,String trx_no,String whereCodnition);
	public String approveRejectRefund(Integer id,String updateValues,String whereCodnition);
	
}
