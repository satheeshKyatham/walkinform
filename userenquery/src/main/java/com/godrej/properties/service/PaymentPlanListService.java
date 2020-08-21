package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanList;

public interface PaymentPlanListService {
	
	List<PaymentPlanList> getPaymentPlanList(String projectCode);

	List<PaymentPlanList> getpaymentPlanWithCIPActive(String projectcode);
	
	
	List<PaymentPlanList> getPPList (String whereCondition);
	
	
	
	void updatePP(PaymentPlanList paymentPlanList);
}
