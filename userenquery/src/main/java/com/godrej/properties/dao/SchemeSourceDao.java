package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.SchemeSource;

public interface SchemeSourceDao {
	List<SchemeSource> getSchemeSource(String projectId, String cp_flag_yn);
	
	void insertSchemeSource(SchemeSource schemeSource);
}