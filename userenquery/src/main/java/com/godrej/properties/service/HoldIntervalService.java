package com.godrej.properties.service;

import com.godrej.properties.model.Inventory;

public interface HoldIntervalService {

	Inventory unitExist(String unitSfid, String projectNameId, String towerCode);
	public Inventory getHeldUnit(String unitSfid);
	
	
	
}
