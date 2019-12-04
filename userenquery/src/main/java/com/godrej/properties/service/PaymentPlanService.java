package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.model.TowerBand;
import com.godrej.properties.model.TowerMaster;
import com.google.gson.JsonElement;

public interface PaymentPlanService {

	

	List<PaymentPlan> getProjectPlan(String project_code, String unit, String towerCode, String pymtPlanSfid);

	List<PaymentPlan> getHouseUnit(String project_code, String tower_code, String floor_code, String unit);

	List<PaymentPlan> getUnitType(String project_code, String tower_code, String floor_code);

	List<PaymentPlan> getfloor(String project_code, String tower_code);
	
	List<PaymentPlan> getTower(String project_code);

	List<TowerBand> getTowerBand(String project_code, String tower_code);

	List<TowerMaster> getTowerMaster(String project_code);
	
 
	

}
