package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.BSPAgainstPymtPlan;

public interface BSPAgainstPymtPlanDao {
	void insertBSPForPP (BSPAgainstPymtPlan bSPAgainstPymtPlan);
	int checkBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan);
	public List<BSPAgainstPymtPlan> getPaymentPlanBSPList(String paymentPlanID);
	
	public List<BSPAgainstPymtPlan> getPaymentPlanPerBSP(String paymentPlanID);
}
