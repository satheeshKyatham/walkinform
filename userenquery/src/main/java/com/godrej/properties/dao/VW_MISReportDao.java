package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Vw_MISReport;

public interface VW_MISReportDao {
	
	List<Vw_MISReport> getUserProjectList(String projectid,String userid,String fromDate,String toDate);

}
