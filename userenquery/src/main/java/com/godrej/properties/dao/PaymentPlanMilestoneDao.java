package com.godrej.properties.dao;
import java.util.List;

import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.model.PaymentPlanMilestone;

public interface PaymentPlanMilestoneDao {
	public List<PaymentPlanMilestone> getPaymentPlanLineItems(String ppSFID);
}