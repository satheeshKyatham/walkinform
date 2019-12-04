package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.OldNewValueDao;
import com.godrej.properties.dao.OrderDataMappingDao;
import com.godrej.properties.model.OrderDataMapping;
import com.godrej.properties.model.ValueOldNew;
import com.godrej.properties.service.OrderDataMapppingService;

@Service("orderDataMapppingService")
@Transactional
public class OrderDataMappingServiceImpl implements OrderDataMapppingService {

	
	@Autowired
	OrderDataMappingDao   orderDataMappingDao;
	
	@Autowired
	OldNewValueDao   oldNewValueDao;
	
	
	
	@Override
	public List<OrderDataMapping> getdataMapping() {
		// TODO Auto-generated method stub
		return orderDataMappingDao.getdataMapping() ;
	}
	@Override
	public List<OrderDataMapping> getdataMapping(String str_date) {
		// TODO Auto-generated method stub
		return orderDataMappingDao.getdataMapping(str_date) ;
	}
	@Override
	public List<ValueOldNew> old_newValueDataSysnc() {
		
		return oldNewValueDao.old_newValueDataSysnc();
	}

	 
	
	 
 
 

}
