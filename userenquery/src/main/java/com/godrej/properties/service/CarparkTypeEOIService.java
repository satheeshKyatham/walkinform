package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CarparkTypeEOI;

public interface CarparkTypeEOIService {
	List<CarparkTypeEOI> getTowerBand(String project_code);
}