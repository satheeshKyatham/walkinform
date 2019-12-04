package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqOnMapDao;
import com.godrej.properties.model.EnqOnMap;
import com.godrej.properties.service.EnqOnMapService;

@Service("enqOnMapService")
@Transactional
public class EnqOnMapServiceImpl implements EnqOnMapService{
	@Autowired
	EnqOnMapDao enqOnMapDao;
	
	@Override
	public List<EnqOnMap> getEnqDtl(String projectId) {
		// TODO Auto-generated method stub
		return enqOnMapDao.getEnqDtl(projectId);
	}
}
