package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.AssignUserDao;
import com.godrej.properties.dao.UserMasterDao;
import com.godrej.properties.dto.VW_UserMasterDao;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.UserMaster;
import com.godrej.properties.model.Vw_UserMaster;
import com.godrej.properties.service.AssignUserService;
import com.godrej.properties.service.UserMasterService;
import com.godrej.properties.service.VW_UserMasterService;

@Service("assignUserService")
@Transactional
public class AssignUserServiceImpl implements AssignUserService {

	
	@Autowired
	AssignUserDao assignUserDao;

	 

	@Override
	public List<AssignedUser> getassignedusers(String user_id,String projectId,String fromdate) {
		// TODO Auto-generated method stub
		return assignUserDao.getassignedusers(user_id,projectId,fromdate);
	}
	 
	 
	  

	 
	
	 
 
 

}
