package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.SchemeSource;

public interface SchemeSourceService {
	List<SchemeSource> getSchemeSource(String projectId, String cp_flag_yn);
	
	void insertSchemeSource(SchemeSource schemeSource);
}