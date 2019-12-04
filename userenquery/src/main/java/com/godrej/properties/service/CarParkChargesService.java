package com.godrej.properties.service;

import com.godrej.properties.model.CarParkCharges;

public interface CarParkChargesService {
	void insertCPAmount (CarParkCharges  carParkCharges);
	
	CarParkCharges getCharges(String parkType, String projectSFID);
}
