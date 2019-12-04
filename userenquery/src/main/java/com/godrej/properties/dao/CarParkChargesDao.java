package com.godrej.properties.dao;

import com.godrej.properties.model.CarParkCharges;

public interface CarParkChargesDao {
	void insertCPAmount (CarParkCharges carParkCharges);
	
	CarParkCharges getCharges (String parkType, String projectSFID);
}
