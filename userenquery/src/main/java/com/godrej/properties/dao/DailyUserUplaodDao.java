package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.Vw_DailyUserMater;

public interface DailyUserUplaodDao {
	
	String uploadDailyUserMaster(DailyUserMater daily);
	List<DailyUserMater> getUploadDailyUserList(String projectID);
	List<Vw_DailyUserMater> getViewUploadDailyUserList(String projectID);
	String updateDailyUserUpload(DailyUserMater daily);
}
