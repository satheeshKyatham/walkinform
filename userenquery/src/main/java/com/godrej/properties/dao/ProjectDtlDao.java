package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ProjectDtl;

public interface ProjectDtlDao {
	
	List<ProjectDtl> getProjectData (String projectId);
	
}
