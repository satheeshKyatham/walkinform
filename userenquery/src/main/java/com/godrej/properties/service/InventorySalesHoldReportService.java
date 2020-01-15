package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.InventorySalesHoldReport;

public interface InventorySalesHoldReportService {
	List<InventorySalesHoldReport> holdDataExist(String whereCondition);
}