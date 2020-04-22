package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanDue;


public interface PaymentPlanDueService {

	public PaymentPlanDue addPaymentPlanDue(PaymentPlanDue paymentDue);

	public List<PaymentPlanDue> getPaymentDueList();

	public PaymentPlanDue updatePaymentDue(PaymentPlanDue data);

	

}
