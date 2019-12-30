package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.TnCEOI;

public interface TnCEOIService {
	void insertTNCForEOI (TnCEOI tnCEOI);
	
	List<TnCEOI> getTncData (String projectid);
}