package com.godrej.properties.dao;
import java.util.List;

import com.godrej.properties.model.PaymentPlanLineItem;

public interface PaymentPlanLineItemDao {

	List<PaymentPlanLineItem> getpaymentplanlist();


	List<PaymentPlanLineItem> getpaymentplanlist(String project_code);

}