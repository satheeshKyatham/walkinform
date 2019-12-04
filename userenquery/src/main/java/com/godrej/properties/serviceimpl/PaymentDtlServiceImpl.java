package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.PaymentDtlDao;
import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.service.PaymentDtlService;

@Service("paymentDtlService")
@Transactional
public class PaymentDtlServiceImpl implements PaymentDtlService{
	
	@Autowired
	private PaymentDtlDao dao;

	@Override
	public void insertPaymentDtl(List<PaymentDtl> pymtDtl) {
		dao.insertPaymentDtl(pymtDtl);
	}

	@Override
	public List<PaymentDtl> getPrePaymentData(String enq) {
		// TODO Auto-generated method stub
		return dao.getPrePaymentData(enq);
	}
}