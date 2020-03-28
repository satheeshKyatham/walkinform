package com.godrej.properties.dto;

import java.util.List;

import com.godrej.properties.model.ProjectLaunch;

public interface ProjectLaunchDao {

	List<ProjectLaunch> getActiveProjectList();
	String UpdateProjectStatus(String id,String status);
	List<ProjectLaunch> getProjectList(String regionid);
	ProjectLaunch getProjectSaleMgrID(String projectid);
	ProjectLaunch getprojectDetailsForCCPaymentQuery(String projectSfid);

}
