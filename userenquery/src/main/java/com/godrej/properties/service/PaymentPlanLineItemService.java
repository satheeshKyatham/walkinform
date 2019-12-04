package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanLineItem;

public interface PaymentPlanLineItemService {
	List<PaymentPlanLineItem> getpaymentplanlist();
	List<PaymentPlanLineItem> getpaymentplanlist(String project_code);

}
