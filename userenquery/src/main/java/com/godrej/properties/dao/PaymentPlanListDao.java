package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanList;

public interface PaymentPlanListDao {
	List<PaymentPlanList> getPaymentPlanList(String projectCode);

	public List<PaymentPlanList> getpaymentPlanWithCIPActiveQuery(String project);
	
	
	List<PaymentPlanList> getPPList(String whereCondition);
	
	
	void updatePP(PaymentPlanList paymentPlanList);
	
}
