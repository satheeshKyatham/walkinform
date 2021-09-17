package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.EOIReportDao;
import com.godrej.properties.model.AllotmentMISReport;
import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.EOIReport;
import com.godrej.properties.model.FacingDashboard;
import com.godrej.properties.model.TowerDashboard;
import com.godrej.properties.model.UnitCategoryCount;
import com.godrej.properties.model.UnitFacingCount;
import com.godrej.properties.model.UnitTowerCount;
import com.godrej.properties.service.EOIReportService;

@Service("eOIReportService")
@Transactional
public class EOIReportServiceImpl implements EOIReportService{
	@Autowired
	EOIReportDao  eOIReportDao;

	@Override
	public List<EOIReport> getEOIReportDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return eOIReportDao.getEOIReportDtl(whereCondition);
	}

	@Override
	public List<AllotmentReport> getAllotmentReport(String whereCondition) {
		// TODO Auto-generated method stub
		return eOIReportDao.getAllotmentReport(whereCondition);
	}

	@Override
	public AllotmentMISReport getAllotmentMISReport(String whereCondition,String fromDate,String toDate) {
		// TODO Auto-generated method stub
		return eOIReportDao.getAllotmentMISReport(whereCondition,fromDate,toDate);
	}
	
	@Override
	public TowerDashboard getTowerdashboard(String whereCondition) {
		return eOIReportDao.getTowerdashboard(whereCondition);
	}
	
	@Override
	public FacingDashboard getFacingdashboard(String whereCondition) {
		return eOIReportDao.getFacingdashboard(whereCondition);
	}
	
	@Override
	public List<UnitFacingCount> getUnitFacingCount(String projectSFID) {
		return eOIReportDao.getUnitFacingCount(projectSFID);
	}
	
	@Override
	public List<UnitTowerCount> getUnitTowerCount(String projectSFID) {
		return eOIReportDao.getUnitTowerCount(projectSFID);
	}
	
	
	@Override
	public List<UnitCategoryCount> getUnitCategoryCount(String projectSFID) {
		return eOIReportDao.getUnitCategoryCount(projectSFID);
	}
}