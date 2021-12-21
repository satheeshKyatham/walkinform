package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ParkingReport;

public interface ParkingReportDao {
	List<ParkingReport>getParkingReportDtl(String whereCondition);
}