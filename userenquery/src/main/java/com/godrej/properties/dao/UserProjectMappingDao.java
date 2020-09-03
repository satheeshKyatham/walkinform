package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.UserProjectMapping;
import com.godrej.properties.model.Vw_UserMaster;

public interface UserProjectMappingDao {

	String projectassignUpdate(UserProjectMapping mapping);
	List<UserProjectMapping> getProjectMappingList(String projectID);
	String projectMappingUpdateStatus(UserProjectMapping mapping);
	List<UserProjectMapping> getUserProjectByQuery(int userId);
}
