package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CustomerDtlDao;
import com.godrej.properties.model.CustomerDtl;
import com.godrej.properties.service.CustomerDtlService;

@Service("customerDtlService")
@Transactional
public class CustomerDtlServiceImpl implements CustomerDtlService{
	@Autowired
	private CustomerDtlDao dao;
	
	@Override
	public CustomerDtl getCustomerDeta(String contactNo) {
		// TODO Auto-generated method stub
		return dao.getCustomerDeta(contactNo);
	}

}
