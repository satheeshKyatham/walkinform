package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.AssignUserDao;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.TodaysFollowUp;
import com.godrej.properties.service.AssignUserService;

@Service("assignUserService")
@Transactional
public class AssignUserServiceImpl implements AssignUserService {

	
	@Autowired
	AssignUserDao assignUserDao;

	 

	@Override
	public List<AssignedUser> getassignedusers(String user_id,String projectId,String fromdate,String todate, String source) {
		// TODO Auto-generated method stub
		return assignUserDao.getassignedusers(user_id,projectId,fromdate,todate, source);
	}
	
	@Override
	public List<TodaysFollowUp> getData(String user_id,String projectId,String fromdate,String todate, String source) {
		// TODO Auto-generated method stub
		return assignUserDao.getData(user_id,projectId,fromdate,todate, source);
	}
	 
	 
	  

	 
	
	 
 
 

}
