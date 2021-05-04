package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.CarparkTypeEOI;
import com.godrej.properties.model.TokenTypeEOI;

public interface CarparkTypeEOIService {
	List<CarparkTypeEOI> getTowerBand(String project_code);
	
	List<TokenTypeEOI> getTokenType(String projectsfid);
}