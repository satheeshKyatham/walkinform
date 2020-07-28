package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanMilestoneDao;
import com.godrej.properties.model.PaymentPlanMilestone;
import com.godrej.properties.service.PaymentPlanMilestoneService;

@Service("paymentPlanMilestoneService")
@Transactional
public class PaymentPlanMilestoneServiceImpl implements PaymentPlanMilestoneService{

	@Autowired
	PaymentPlanMilestoneDao paymentPlanMilestoneDao;
	
	@Override
	public List<PaymentPlanMilestone> getPaymentPlanLineItems(String ppSFID) {
		List<PaymentPlanMilestone> paymentPlanLineData = paymentPlanMilestoneDao.getPaymentPlanLineItems(ppSFID);
		return paymentPlanLineData;
	}
}