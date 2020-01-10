package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.InventoryReport;

public interface InventoryReportService {
	List<InventoryReport> getInventoryReportDtl(String whereCondition);
}