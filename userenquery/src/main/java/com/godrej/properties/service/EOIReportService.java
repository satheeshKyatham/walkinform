package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.AllotmentMISReport;
import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.CategoryTowerCount;
import com.godrej.properties.model.EOIReport;
import com.godrej.properties.model.FacingDashboard;
import com.godrej.properties.model.TowerDashboard;
import com.godrej.properties.model.UnitCategoryCount;
import com.godrej.properties.model.UnitFacingCount;
import com.godrej.properties.model.UnitTowerCount;

public interface EOIReportService {
	List<EOIReport> getEOIReportDtl(String whereCondition);
	List<AllotmentReport>getAllotmentReport(String whereCondition);
	public AllotmentMISReport getAllotmentMISReport(String whereCondition,String fromDate,String toDate);
	
	public TowerDashboard getTowerdashboard(String whereCondition);
	
	public FacingDashboard getFacingdashboard(String whereCondition);
	
	List<UnitFacingCount> getUnitFacingCount(String projectSFID);
	List<UnitTowerCount> getUnitTowerCount(String projectSFID);
	
	
	List<UnitCategoryCount> getUnitCategoryCount(String projectSFID);
	
	
	List<CategoryTowerCount> getCategoryTowerCount(String projectSFID);
}