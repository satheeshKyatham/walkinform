package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping;

public interface VW_UserMasterService {

	List<Vw_UserMaster> getUserProjectList(String userid);

	Vw_UserMaster getUserDetails(int user_id);

	List<Vw_UserMaster> getUserListProjectWise(String projectID);

	Vw_UserMaster getUserDetails(int user_id, String projectId);
	List<Vw_UserProjectMapping> getUserProjectMapping(String projectID);
	List<Vw_UserProjectMapping> getProjectListUserWise(String userid);

	List<Vw_UserProjectMapping> getOfferApprovalUser(String projectid);
	
	
}
