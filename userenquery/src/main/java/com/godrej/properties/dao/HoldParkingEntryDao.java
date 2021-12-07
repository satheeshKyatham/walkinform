package com.godrej.properties.dao;

import com.godrej.properties.model.HoldParkingEntry;

public interface HoldParkingEntryDao {
	public void insertHoldRqst (HoldParkingEntry action); 
	
	public void updateForelease(HoldParkingEntry action);
	
	public int getCurrentVersion(String parkingsfid);
	
	public void updateParkingPreviousHold(HoldParkingEntry action);
}