package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EOIPreferenceDtl;

public interface EOIPreferenceDtlDao {
	void insertEOI (List<EOIPreferenceDtl> eoiDtl);
	List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid);
	Boolean updateEOIPreference(List<EOIPreferenceDtl> eoiReq);
}