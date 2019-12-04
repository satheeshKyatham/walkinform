package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentSheet;


public interface PaymentSheetDao {

	List<PaymentSheet> getpaymentsheet(String project_c);

}
