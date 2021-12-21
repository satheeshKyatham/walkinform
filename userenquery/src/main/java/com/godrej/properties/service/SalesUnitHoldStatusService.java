package com.godrej.properties.service;

import com.godrej.properties.model.HoldInventoryEntry;

public interface SalesUnitHoldStatusService {
	
	Boolean getSalesUnitHold(String unitSFID, String userid);
	
	HoldInventoryEntry getUnitHoldDtl(String unitSFID);
}