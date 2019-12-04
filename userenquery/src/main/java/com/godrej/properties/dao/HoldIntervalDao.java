package com.godrej.properties.dao;

import com.godrej.properties.model.Inventory;

public interface HoldIntervalDao {
	Inventory unitExist (String unitSfid, String projectNameId, String towerCode);
	public Inventory getHeldUnit(String unitSfid);
}
