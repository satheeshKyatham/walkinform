package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ProjectRegion;
import com.google.gson.JsonElement;

public interface ProjectRegionService {
	
	List<ProjectRegion> getRegion();

	List<ProjectRegion> getProjectData(String region);

	ProjectRegion getRegionForTnc(String projectid);
	
}
