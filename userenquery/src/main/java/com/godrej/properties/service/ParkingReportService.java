package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ParkingReport;

public interface ParkingReportService {
	List<ParkingReport> getParkingReportDtl(String whereCondition);
}