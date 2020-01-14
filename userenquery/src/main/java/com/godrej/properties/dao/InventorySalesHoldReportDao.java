package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.InventorySalesHoldReport;

public interface InventorySalesHoldReportDao {
	public List<InventorySalesHoldReport> holdDataExist(String whereCondition);
}