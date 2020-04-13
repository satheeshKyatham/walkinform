package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.UserMasterDao;
import com.godrej.properties.dto.VW_UserMasterDao;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.model.Vw_UserProjectMapping;
import com.godrej.properties.service.UserMasterService;
import com.godrej.properties.service.VW_UserMasterService;

@Service("vW_UserMasterService")
@Transactional
public class VW_UserMasterServiceImpl implements VW_UserMasterService {

	
	@Autowired
	 VW_UserMasterDao vw_UserMasterDao;

	@Override
	public List<Vw_UserMaster> getUserProjectList(String userid) {
		return vw_UserMasterDao.getUserProjectList(userid);
	}

	@Override
	public Vw_UserMaster getUserDetails(int user_id) {
		return vw_UserMasterDao.getUserDetails(user_id);
	}

	@Override
	public List<Vw_UserMaster> getUserListProjectWise(String projectID) {
		return vw_UserMasterDao.getUserListProjectWise(projectID);
	}

	@Override
	public Vw_UserMaster getUserDetails(int user_id, String projectId) {
		return vw_UserMasterDao.getUserDetails(user_id,projectId);
	}

	@Override
	public List<Vw_UserProjectMapping> getUserProjectMapping(String projectID) {
		return vw_UserMasterDao.getUserProjectMapping(projectID);
	}

	@Override
	public List<Vw_UserProjectMapping> getProjectListUserWise(String userid) {
		return vw_UserMasterDao.getProjectListUserWise(userid);
	}

	@Override
	public List<Vw_UserProjectMapping> getOfferApprovalUser(String projectid) {
		return vw_UserMasterDao.getOfferApprovalUser(projectid);
	}

	@Override
	public List<Vw_UserProjectMapping> getUserProjectMappingTeamLead(String projectID, String condition) {
		List<Vw_UserProjectMapping> teamLeadList = vw_UserMasterDao.getUserProjectMappingTeamLead(projectID,condition);
		if(teamLeadList==null)
			teamLeadList=vw_UserMasterDao.getUserProjectMapping(projectID);
		return teamLeadList;
	}

	 
	 
	  

	 
	
	 
 
 

}
