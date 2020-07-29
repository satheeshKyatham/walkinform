package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanMilestone;

public interface PaymentPlanMilestoneService {
	List<PaymentPlanMilestone> getPaymentPlanLineItems(String ppSFID);
}
