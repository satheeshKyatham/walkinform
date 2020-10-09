package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Vw_MISReport;

public interface VW_MISReportService {
	
	List<Vw_MISReport> getUserProjectList(String projectid,String userid,String fromDate,String toDate, String userVerticals);


}
