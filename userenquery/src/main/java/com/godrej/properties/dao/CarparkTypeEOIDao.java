package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.CarparkTypeEOI;

public interface CarparkTypeEOIDao {
	List<CarparkTypeEOI> getTowerBand(String project_code);
}