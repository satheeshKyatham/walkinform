package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.WithoutOtherChargesPP;

public interface WithoutOtherChargesPPService {
	List<WithoutOtherChargesPP> getPPData(String paymentPlanSfid);
}
