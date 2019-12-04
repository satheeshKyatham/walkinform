package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.UserProjectMappingList;

public interface UserProjectMappingListDao {

	List<UserProjectMappingList> getProjectMappingList(String projectID);

}
