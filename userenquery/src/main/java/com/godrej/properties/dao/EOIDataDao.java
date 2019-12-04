package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EOIRecords;

public interface EOIDataDao {
	List<EOIRecords>getEOIDtl(String whereCondition);
}