package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqDtlProjectWiseDao;
import com.godrej.properties.model.EnqDtlProjectWise;
import com.godrej.properties.service.EnqDtlProjectWiseService;

@Service("enqDtlProjectWiseService")
@Transactional
public class EnqDtlProjectWiseServiceImpl implements EnqDtlProjectWiseService{
	@Autowired
	EnqDtlProjectWiseDao enqDtlProjectWiseDao;
	
	@Override
	public List<EnqDtlProjectWise> getEnqForAdminUnitHold(String enqName, String projectSFID) {
		return enqDtlProjectWiseDao.getEnqForAdminUnitHold(enqName, projectSFID);
	}
}