package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.BSPAgainstPymtPlan;

public interface BSPAgainstPymtPlanService {
	void insertBSPForPP (BSPAgainstPymtPlan bSPAgainstPymtPlan);
	int checkBSPForPP(BSPAgainstPymtPlan bSPAgainstPymtPlan);
	public int getPaymentPlanBSPList(String project_code,String unit,String towerCode,String paymentPlanID,String typology);
}
