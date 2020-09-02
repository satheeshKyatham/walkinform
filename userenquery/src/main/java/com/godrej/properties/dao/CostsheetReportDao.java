package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CostsheetReport;

public interface CostsheetReportDao {
	List<CostsheetReport>getReportDtl(String whereCondition);
}