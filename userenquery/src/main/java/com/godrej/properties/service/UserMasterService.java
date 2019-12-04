package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.UserMaster;

public interface UserMasterService {

	public String saveUser(UserMaster userMaster);

	public UserMaster searchUser(UserMaster userMaster, String type);

	public UserMaster updateUser(UserMaster userMaster);

	public List<UserMaster> getUserList(String projectID);

	public String updateUserMaster(UserMaster updatemaster);

	public List<UserMaster> getActiveUserList();
	
	public UserMaster getUserDetails(String userID);

}
