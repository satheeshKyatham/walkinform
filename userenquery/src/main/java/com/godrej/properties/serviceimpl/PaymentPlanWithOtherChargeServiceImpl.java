package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanWithOtherChargeDao;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;
import com.godrej.properties.service.PaymentPlanWithOtherChargeService;

@Service("paymentPlanWithOtherChargeService")
@Transactional

public class PaymentPlanWithOtherChargeServiceImpl implements PaymentPlanWithOtherChargeService{
	@Autowired
	private PaymentPlanWithOtherChargeDao payment;
	
	@Override
	public List<PaymentPlanWithOtherCharge> getppAndOtherCharges(String unitSfid, String paymentPlanSfid,String projectid) {
		// TODO Auto-generated method stub
		return payment.getppAndOtherCharges(unitSfid, paymentPlanSfid,projectid);
	}
}