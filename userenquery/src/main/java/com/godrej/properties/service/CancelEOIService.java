package com.godrej.properties.service;

public interface CancelEOIService {
	String deleteEOI(String preferenceJson, String paymentJson, String unitsfidOldArray, String userid, String enq_sfid, String project_sfid, String username, String enqid);
}