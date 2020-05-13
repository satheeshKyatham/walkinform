package com.godrej.properties.dao;

public interface CancelEOIDao {
	Boolean updateEOI(String enq_sfid, String project_sfid, String enqid);
}