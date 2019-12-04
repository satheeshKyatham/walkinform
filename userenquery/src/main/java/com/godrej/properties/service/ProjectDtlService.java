package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ProjectDtl;


public interface ProjectDtlService {
	List<ProjectDtl> getProjectData (String projectId);
}
