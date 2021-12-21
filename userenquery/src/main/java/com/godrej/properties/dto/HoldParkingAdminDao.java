package com.godrej.properties.dto;

import com.godrej.properties.model.HoldParkingAdmin;

public interface HoldParkingAdminDao {

	void saveHoldInventoryAdmin(HoldParkingAdmin parkingAdmin); 
	
	void updateHoldParkingAdmin(HoldParkingAdmin parkingAdmin);

}