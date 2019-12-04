package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlanWithOtherCharge;

public interface PaymentPlanWithOtherChargeDao {
	List<PaymentPlanWithOtherCharge> getppAndOtherCharges(String unitSfid, String paymentPlanSfid,String projectid);
}
