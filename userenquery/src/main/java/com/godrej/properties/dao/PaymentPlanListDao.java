package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanList;

public interface PaymentPlanListDao {
	List<PaymentPlanList> getPaymentPlanList(String projectCode);
}
