package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.AdLoginUserDao;
import com.godrej.properties.model.ADLoginPass;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.service.AdLoginUserService;

@Service("adLoginUserService")
@Transactional
public class AdLoginUserServiceImpl implements AdLoginUserService {

	
	@Autowired
	AdLoginUserDao assignUserDao;

 


	@Override
	public List<ADLoginPass> getUserList(String emailid) {
		// TODO Auto-generated method stub
		return assignUserDao.getUserList(emailid);
	}
	 
	 
	  

	 
	
	 
 
 

}
