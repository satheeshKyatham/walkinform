package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentEOIReport;

public interface PaymentEOIReportService {
	List<PaymentEOIReport> getPaymentEOIReportDtl(String whereCondition);
}