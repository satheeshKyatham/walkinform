package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dto.ProjectLaunchDao;
import com.godrej.properties.model.ProjectLaunch;
import com.godrej.properties.service.ProjectLaunchService;

@Service("projectLaunchService")
@Transactional
public class ProjectLaunchServiceImpl implements ProjectLaunchService {

	@Autowired
	ProjectLaunchDao projectLaunchDao;

	@Override
	public List<ProjectLaunch> getActiveProjectList() {
		// TODO Auto-generated method stub
		return projectLaunchDao.getActiveProjectList();
	}

	@Override
	public String UpdateProjectStatus(String id, String status) {
		// TODO Auto-generated method stub
		return projectLaunchDao.UpdateProjectStatus(id, status);
	}

	@Override
	public List<ProjectLaunch> getProjectList(String regionid) {
		return projectLaunchDao.getProjectList(regionid);
	}

	@Override
	public ProjectLaunch getProjectSaleMgrID(String projectid) {
		return projectLaunchDao.getProjectSaleMgrID(projectid);
	}

	@Override
	public ProjectLaunch getprojectDetailsForCCPayment(String projectSfid) {
		// TODO Auto-generated method stub
		return projectLaunchDao.getprojectDetailsForCCPaymentQuery(projectSfid);
	}
	
	 

}
