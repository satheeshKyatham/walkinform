package com.godrej.properties.serviceimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.DailyUserUplaodDao;
import com.godrej.properties.dao.UserProjectMappingDao;
import com.godrej.properties.dao.UserProjectMappingListDao;
import com.godrej.properties.model.DailyUserMater;
import com.godrej.properties.model.UserProjectMapping;
 
import com.godrej.properties.model.UserProjectMappingList;
 
import com.godrej.properties.model.Vw_DailyUserMater;
import com.godrej.properties.service.DailyUserUplaodService;

@Service("dailyUserUplaodService")
@Transactional
public class DailyUserUplaodServiceImpl implements DailyUserUplaodService{

	@Autowired
	private DailyUserUplaodDao dailyUserUplaodDao;
	
	@Autowired
	private UserProjectMappingDao userProjectMappingDao;
	
	@Autowired
	private UserProjectMappingListDao userProjectMappingListDao;
	
	@Override
	public String uploadDailyUserMaster(DailyUserMater daily) {
		// TODO Auto-generated method stub
		return dailyUserUplaodDao.uploadDailyUserMaster(daily);
	}

	@Override
	public String projectassignUpdate(UserProjectMapping mapping) {
		// TODO Auto-generated method stub
		return userProjectMappingDao.projectassignUpdate(mapping);
	}

	@Override
	public List<UserProjectMappingList> getProjectMappingList(String projectID) {
		// TODO Auto-generated method stub
		return userProjectMappingListDao.getProjectMappingList(projectID);
	}

	@Override
	public String projectMappingUpdateStatus(UserProjectMapping mapping) {
		// TODO Auto-generated method stub
		return userProjectMappingDao.projectMappingUpdateStatus(mapping);
	}

	@Override
	public List<DailyUserMater> getUploadDailyUserList(String projectID) {
		// TODO Auto-generated method stub
		return dailyUserUplaodDao.getUploadDailyUserList(projectID);
	}

	@Override
	public String updateDailyUserUpload(DailyUserMater daily) {
		// TODO Auto-generated method stub
		return dailyUserUplaodDao.updateDailyUserUpload(daily);
	}

	@Override
	public List<Vw_DailyUserMater> getViewUploadDailyUserList(String projectID) {
		// TODO Auto-generated method stub
		return dailyUserUplaodDao.getViewUploadDailyUserList(projectID);
	}

}
