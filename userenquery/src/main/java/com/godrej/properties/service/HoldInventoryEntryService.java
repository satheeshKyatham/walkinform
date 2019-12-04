package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.Inventory;

public interface HoldInventoryEntryService {
	void insertHoldRqst (HoldInventoryEntry action);
	
	HoldInventoryEntry holdExist(String projectNameId, String customerId);
	
	void updateForelease (HoldInventoryEntry action);
	
	void updatePreviousHold (HoldInventoryEntry action);
	
	List<HoldInventoryEntry> holdDataExist(String projectNameId, String customerId);
	
	public int getCurrentVersion(String projectId, String unitId, String customerId);
	public HoldInventoryEntry getHolding(int userId) ;

}
