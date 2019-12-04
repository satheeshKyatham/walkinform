package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.BillingViewDao;
import com.godrej.properties.dao.OrderDataMappingDao;
import com.godrej.properties.model.BillingData;
import com.godrej.properties.model.OrderDataMapping;
import com.godrej.properties.service.BillingViewService;
import com.godrej.properties.service.OrderDataMapppingService;

@Service("billingViewService")
@Transactional
public class BillingViewServiceImpl implements BillingViewService {

	
	@Autowired
	BillingViewDao   billingViewDao;
 
	@Override
	public List<BillingData> getdataMapping(String page, String count, String str_date) {
		// TODO Auto-generated method stub
		return billingViewDao.getdataMapping(page,count,str_date) ;
	}

	@Override
	public String getdataCount(String model,String str_date) {
		// TODO Auto-generated method stub
		return billingViewDao.getdataCount(model,str_date) ;
	}

	@Override
	public List<BillingData> getCreateTemp() {
		// TODO Auto-generated method stub
		return billingViewDao.getCreateTemp() ;
	}

	 
	
	 
 
 

}
