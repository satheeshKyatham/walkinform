package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.PaymentPlanListDao;
import com.godrej.properties.model.PaymentPlanList;
import com.godrej.properties.service.PaymentPlanListService;

@Service("paymentPlanListService")
@Transactional
public class PaymentPlanListServiceImpl implements PaymentPlanListService{
	
	@Autowired
	PaymentPlanListDao paymentPlanListDao;
	
	@Override
	public List<PaymentPlanList> getPaymentPlanList(String projectCode) {
		// TODO Auto-generated method stub
		return paymentPlanListDao.getPaymentPlanList(projectCode);
	}
	
}
