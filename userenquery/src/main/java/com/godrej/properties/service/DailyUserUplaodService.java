package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.UserProjectMapping;
 
import com.godrej.properties.model.UserProjectMappingList;
 
import com.godrej.properties.model.Vw_DailyUserMater;

public interface DailyUserUplaodService {

	String uploadDailyUserMaster(DailyUserMater daily);
	List<DailyUserMater> getUploadDailyUserList(String projectID);
	List<Vw_DailyUserMater> getViewUploadDailyUserList(String projectID);
	String updateDailyUserUpload(DailyUserMater daily);
	
	String projectassignUpdate(UserProjectMapping mapping);
	List<UserProjectMappingList> getProjectMappingList(String projectID);
	String projectMappingUpdateStatus(UserProjectMapping mapping);
	
	
}
