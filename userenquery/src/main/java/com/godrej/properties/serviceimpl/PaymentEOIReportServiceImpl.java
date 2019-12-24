package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentEOIReportDao;
import com.godrej.properties.model.PaymentEOIReport;
import com.godrej.properties.service.PaymentEOIReportService;

@Service("paymentEOIReportService")
@Transactional
public class PaymentEOIReportServiceImpl implements PaymentEOIReportService{
	@Autowired
	PaymentEOIReportDao  paymentEOIReportDao;

	@Override
	public List<PaymentEOIReport> getPaymentEOIReportDtl(String whereCondition) {
		return paymentEOIReportDao.getPaymentEOIReportDtl(whereCondition);
	}
}