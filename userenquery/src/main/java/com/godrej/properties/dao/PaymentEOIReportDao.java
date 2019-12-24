package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentEOIReport;

public interface PaymentEOIReportDao {
	List<PaymentEOIReport>getPaymentEOIReportDtl(String whereCondition);
}