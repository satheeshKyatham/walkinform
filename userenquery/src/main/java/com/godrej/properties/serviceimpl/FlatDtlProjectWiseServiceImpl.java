package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.FlatDtlProjectWiseDao;
import com.godrej.properties.model.FlatDtlProjectWise;
import com.godrej.properties.service.FlatDtlProjectWiseService;

@Service("flatDtlProjectWiseService")
@Transactional
public class FlatDtlProjectWiseServiceImpl implements FlatDtlProjectWiseService{
	@Autowired
	FlatDtlProjectWiseDao flatDtlProjectWiseDao;
	
	@Override
	public List<FlatDtlProjectWise> getFlatForAdminUnitHold(String flatName, String projectSFID) {
		return flatDtlProjectWiseDao.getFlatForAdminUnitHold(flatName, projectSFID);
	}
}