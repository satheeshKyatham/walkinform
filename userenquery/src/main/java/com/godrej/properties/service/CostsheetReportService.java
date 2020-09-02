package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CostsheetReport;

public interface CostsheetReportService {
	List<CostsheetReport> getReportDtl(String whereCondition);
}