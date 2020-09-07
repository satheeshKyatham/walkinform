package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ProjectLaunch;

public interface ProjectLaunchService {

	List<ProjectLaunch> getActiveProjectList();

	List<ProjectLaunch> getProjectList(String regionid);
	String UpdateProjectStatus(String id,String status);
	ProjectLaunch getProjectSaleMgrID(String projectid);
	ProjectLaunch getprojectDetails(String projectSfid);

}
