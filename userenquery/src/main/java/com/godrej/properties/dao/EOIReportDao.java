package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ApplicationDtl;
import com.godrej.properties.model.EOIReport;

public interface EOIReportDao {
	List<EOIReport>getEOIReportDtl(String whereCondition);
}