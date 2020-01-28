package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.AllotmentMISReport;
import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.EOIReport;

public interface EOIReportDao {
	List<EOIReport>getEOIReportDtl(String whereCondition);
	List<AllotmentReport>getAllotmentReport(String whereCondition);
	public AllotmentMISReport getAllotmentMISReport(String whereCondition);
}