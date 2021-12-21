package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.AllParkingData;

public interface AllParkingDataService {
	List<AllParkingData> getParkingReportDtl(String whereCondition);
}