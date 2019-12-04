package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIReport;

public interface EOIReportService {
	List<EOIReport> getEOIReportDtl(String whereCondition);
}