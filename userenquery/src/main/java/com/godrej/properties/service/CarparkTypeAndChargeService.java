package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.dto.SoldCarParkDTO;
import com.godrej.properties.model.CarParkingMapping;
import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;

public interface CarparkTypeAndChargeService {
	List<CarparkTypeAndCharge> getCarparkType(String projectSFID);
	
	List<CarparkCount> getCarparkCount(String projectSFID);
	List<CarParkingMapping> getCarParkingCombination(String towerSFID);
	List<CarParkingMapping> insertCarParkingCombination(List<CarParkingMapping> carParkingMapping);
	String inActiveCarParkingCombination(String property_type_sfid,String parking_category,String isactive);
	
	List<SoldCarParkDTO> getCarParkingCount(String projectsfid);
}