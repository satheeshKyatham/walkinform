package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.InventoryReport;

public interface InventoryReportDao {
	List<InventoryReport>getInventoryReportDtl(String whereCondition);
}