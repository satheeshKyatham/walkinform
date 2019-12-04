package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlanWithOtherCharge;

public interface PaymentPlanWithOtherChargeService {
	List<PaymentPlanWithOtherCharge> getppAndOtherCharges(String unitSfid, String paymentPlanSfid,String projectid);
}
