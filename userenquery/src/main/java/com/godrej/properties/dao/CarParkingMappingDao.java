package com.godrej.properties.dao;

import com.godrej.properties.model.CarParkingMapping;

public interface CarParkingMappingDao {

	CarParkingMapping insertCarParkingCombination(CarParkingMapping carParkingMapping);
	CarParkingMapping updateCarParkingCombination(CarParkingMapping carParkingMapping);
	CarParkingMapping selectCarParkingCombination(CarParkingMapping carParkingMapping);
	String inActiveCarParkingCombination(String property_type_sfid,String parking_category,String isactive);
}
