package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.EOIDataDao;
import com.godrej.properties.model.EOIRecords;
import com.godrej.properties.service.EOIDataService;

@Service("eOIDataService")
@Transactional
public class EOIDataServiceImpl implements EOIDataService{
	@Autowired	
	EOIDataDao  eOIDataDao;

	@Override	
	public List<EOIRecords> getEOIDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return eOIDataDao.getEOIDtl(whereCondition);
	}
	
	
}