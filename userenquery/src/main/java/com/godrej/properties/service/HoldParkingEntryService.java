package com.godrej.properties.service;

import com.godrej.properties.model.HoldParkingEntry;

public interface HoldParkingEntryService {
	void insertHoldRqst (HoldParkingEntry action);
	
	public int releaseInventory(String parkingsfid, Integer userid, String flatsfid);
	
	void updateForelease(HoldParkingEntry action);
	int getCurrentVersion(String parkingsfid);
	
	void updateParkingPreviousHold (HoldParkingEntry action);
}