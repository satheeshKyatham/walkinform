package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.UserMasterDao;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.service.UserMasterService;

@Service("userMasterService")
@Transactional
public class UserMasterServiceImpl implements UserMasterService {

	
	@Autowired
	UserMasterDao userMasterDao;
	 
	@Override
	public String saveUser(UserMaster userMaster) {
		return userMasterDao.saveUser(userMaster);
		
	}
	@Override
	public UserMaster searchUser(UserMaster userMaster,String type) {
		return userMasterDao.searchUser(userMaster,type);
		
	}
	@Override
	public UserMaster updateUser(UserMaster userMaster) {
		// TODO Auto-generated method stub
		return	userMasterDao.updateUser(userMaster);
	}
	@Override
	public List<UserMaster> getUserList(String projectID) {
		// TODO Auto-generated method stub
		return userMasterDao.getUserList(projectID);
	}
	@Override
	public String updateUserMaster(UserMaster updatemaster) {
		// TODO Auto-generated method stub
		return userMasterDao.updateUserMaster(updatemaster);
	}
	@Override
	public List<UserMaster> getActiveUserList() {
		// TODO Auto-generated method stub
		return userMasterDao.getActiveUserList();
	}
	@Override
	public UserMaster getUserDetails(String userID) {
		// TODO Auto-generated method stub
		return userMasterDao.getUserDetails(userID);
	}

	 
	
	 
 
 

}
