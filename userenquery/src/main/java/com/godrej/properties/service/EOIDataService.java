package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIRecords;

public interface EOIDataService {
	List<EOIRecords> getEOIDtl(String whereCondition);
	
}