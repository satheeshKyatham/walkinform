package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanLineItemDao;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.service.PaymentPlanLineItemService;

@Service("paymentPlanLineItemService")
@Transactional
public class PaymentPlanLineItemServiceImpl implements PaymentPlanLineItemService{

	@Autowired
	private PaymentPlanLineItemDao payment;  
	
		@Override
	public List<PaymentPlanLineItem> getpaymentplanlist() {
		// TODO Auto-generated method stub
		return payment.getpaymentplanlist();
	}
	
	@Override
	public List<PaymentPlanLineItem> getpaymentplanlist(String project_code) {
		// TODO Auto-generated method stub
		return payment.getpaymentplanlist(project_code);
	}

 

}
