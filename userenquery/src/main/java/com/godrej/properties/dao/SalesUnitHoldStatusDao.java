package com.godrej.properties.dao;

import com.godrej.properties.model.HoldInventoryEntry;

public interface SalesUnitHoldStatusDao {
	//List<HoldInventoryAdmin> getAdminUnitHold(String unitSFID);
	
	Boolean getSalesUnitHold(String unitSFID, String userid);
	
	HoldInventoryEntry getUnitHoldDtl(String unitSFID, Integer userid);
}