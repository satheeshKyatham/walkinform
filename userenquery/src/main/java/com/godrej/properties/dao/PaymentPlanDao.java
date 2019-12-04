package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.PaymentPlan;

public interface PaymentPlanDao {
 

	List<PaymentPlan> getProjectPlan(String project_code, String unit, String towerCode, String pymtPlanSfid);

	List<PaymentPlan> getTower(String project_code);

	List<PaymentPlan> getHouseUnit(String project_code, String tower_code, String floor_code, String unit);

	List<PaymentPlan> getUnitType(String project_code, String tower_code, String floor_code);

	List<PaymentPlan> getfloor(String project_code, String tower_code);

}
