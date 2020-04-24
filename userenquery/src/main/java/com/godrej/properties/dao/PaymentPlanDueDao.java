package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanDue;

public interface PaymentPlanDueDao {

	public PaymentPlanDue insertPaymentDue(PaymentPlanDue paymentDue);

	public List<PaymentPlanDue> getPaymentDueListQuery(String project_sfid,String tower_sfid,String payment_plan_sfid);

	public boolean updatePaymentDueQuery(PaymentPlanDue setDto);

}
