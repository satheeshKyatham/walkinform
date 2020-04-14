package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EOIPreferenceDtl;

public interface EOIPreferenceDtlService {
	void insertEOI(List<EOIPreferenceDtl> eoiDtl);
	List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid);
	String updateEOIPreference(String preferenceJson, String userid, String enq_sfid, String project_sfid, String unitsfidOldArray, String username, String newUnitsfidVal);
}