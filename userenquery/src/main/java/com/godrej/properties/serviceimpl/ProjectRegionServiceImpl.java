package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.PaymentPlanDao;
import com.godrej.properties.dao.ProjectRegionDao;
import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.model.ProjectRegion;
import com.godrej.properties.service.PaymentPlanService;
import com.godrej.properties.service.ProjectRegionService;
import com.google.gson.JsonElement;

@Service("projectRegionService")
@Transactional
public class ProjectRegionServiceImpl implements ProjectRegionService {
	
	@Autowired
	ProjectRegionDao projectRegionDao;
	
	
	@Override
	public List<ProjectRegion> getRegion() {
		// TODO Auto-generated method stub
		return projectRegionDao.getRegion();
	}


	@Override
	public List<ProjectRegion> getProjectData(String region) {
		// TODO Auto-generated method stub
		return projectRegionDao.getProjectData(region);
	}


	@Override
	public ProjectRegion getRegionForTnc(String projectid) {
		
		return projectRegionDao.getRegionForTncQuery(projectid);
	}
	
}
