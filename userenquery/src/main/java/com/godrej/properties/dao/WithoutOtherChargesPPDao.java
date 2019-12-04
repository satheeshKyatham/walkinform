package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.WithoutOtherChargesPP;

public interface WithoutOtherChargesPPDao {
	
	List<WithoutOtherChargesPP> getPPData(String paymentPlanSfid);
	
}
