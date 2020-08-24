package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ProjectRegion;

public interface ProjectRegionDao {
	List<ProjectRegion> getRegion();

	List<ProjectRegion> getProjectData(String region);

	ProjectRegion getRegionForTncQuery(String projectid);
}
