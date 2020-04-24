package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanDue;
import com.godrej.properties.model.PaymentPlanLineItem;


public interface PaymentPlanDueService {

	public PaymentPlanDue addPaymentPlanDue(PaymentPlanDue paymentDue);

//	public List<PaymentPlanDue> getPaymentDueList(String project_sfid,String tower_sfid,String payment_plan_sfid);
	public String getPaymentDueList(String project_sfid,String tower_sfid,String payment_plan_sfid);
	
	public PaymentPlanDue updatePaymentDue(PaymentPlanDue data);

	

}
