package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.TnCEOI;

public interface TnCEOIDao {
	void insertTNCForEOI (TnCEOI tnCEOI);
	
	List<TnCEOI> getTncData (String projectid);
}
