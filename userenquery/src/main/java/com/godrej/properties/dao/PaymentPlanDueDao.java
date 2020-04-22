package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanDue;

public interface PaymentPlanDueDao {

	public PaymentPlanDue insertPaymentDue(PaymentPlanDue paymentDue);

	public List<PaymentPlanDue> getPaymentDueListQuery();

	public boolean updatePaymentDueQuery(PaymentPlanDue setDto);

}
