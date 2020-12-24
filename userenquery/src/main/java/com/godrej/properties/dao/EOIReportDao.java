package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.AllotmentMISReport;
import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.EOIReport;
import com.godrej.properties.model.FacingDashboard;
import com.godrej.properties.model.TowerDashboard;
import com.godrej.properties.model.UnitFacingCount;
import com.godrej.properties.model.UnitTowerCount;

public interface EOIReportDao {
	List<EOIReport>getEOIReportDtl(String whereCondition);
	List<AllotmentReport>getAllotmentReport(String whereCondition);
	public AllotmentMISReport getAllotmentMISReport(String whereCondition);
	
	public TowerDashboard getTowerdashboard(String whereCondition);
	public FacingDashboard getFacingdashboard(String whereCondition);
	
	List<UnitFacingCount> getUnitFacingCount(String projectSFID);
	List<UnitTowerCount> getUnitTowerCount(String projectSFID);
}