package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.UserProjectMappingDao;
import com.godrej.properties.model.UserProjectMapping;
import com.godrej.properties.service.UserProjectMappingService;
@Service("userProjectMappingService")
@Transactional
public class UserProjectMappingServiceImpl implements UserProjectMappingService{

	@Autowired
	private UserProjectMappingDao userProjectMappingDao;
	
	@Override
	public List<UserProjectMapping> getUserProject(int userId) {
		// TODO Auto-generated method stub
		return userProjectMappingDao.getUserProjectByQuery(userId);
	}

}
