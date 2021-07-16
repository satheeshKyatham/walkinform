package com.godrej.properties.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.DashboardDao;
import com.godrej.properties.model.DashboardReport;
import com.godrej.properties.service.DashboardService;

@Service("dashboardService")
@Transactional
public class DashboardServiceImpl implements DashboardService{
	@Autowired
	DashboardDao  dashboardDao;

	@Override
	public DashboardReport getDashboard(String projectSFID, String userid) {
		return dashboardDao.getDashboard(projectSFID,userid);
	} 
}