package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.TnC;

public interface TnCService {
	void insertTNCForPP (TnC tnC);
	
	List<TnC> getTncData (String ppId, String projectid);
}
