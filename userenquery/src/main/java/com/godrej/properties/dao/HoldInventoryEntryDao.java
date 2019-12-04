package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.HoldInventoryEntry;

public interface HoldInventoryEntryDao {
	
	public void insertHoldRqst (HoldInventoryEntry action);
	
	public HoldInventoryEntry holdExist(String projectNameId, String customerId);

	public void updateForelease(HoldInventoryEntry action);
	
	public void updatePreviousHold(HoldInventoryEntry action);
	
	public List<HoldInventoryEntry> holdDataExist(String projectNameId, String customerId);
	
	public int getCurrentVersion(String projectId, String unitId, String customerId);
	public HoldInventoryEntry getHolding(int userId) ;
}