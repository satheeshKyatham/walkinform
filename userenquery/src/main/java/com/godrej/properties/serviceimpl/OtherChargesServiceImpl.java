package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OtherChargesDao;
import com.godrej.properties.model.OtherCharges;
import com.godrej.properties.service.OtherChargesService;

@Service("otherChargesService")
@Transactional
public class OtherChargesServiceImpl implements OtherChargesService{
	
	@Autowired
	private OtherChargesDao dao;
	
	public void insertOtherCharge(OtherCharges otherCharges) {
		dao.insertOtherCharge(otherCharges);
	}
	
	
	
}
