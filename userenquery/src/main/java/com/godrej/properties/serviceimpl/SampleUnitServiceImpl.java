package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SampleUnitDao;
import com.godrej.properties.model.SampleUnit;
import com.godrej.properties.service.SampleUnitService;

@Service("sampleUnitService")
@Transactional
public class SampleUnitServiceImpl implements SampleUnitService{
	@Autowired
	SampleUnitDao sampleUnitDao;
	
	@Override
	public SampleUnit geteoiSampleUnit(String condition) {
		return sampleUnitDao.geteoiSampleUnit(condition);
	}
}