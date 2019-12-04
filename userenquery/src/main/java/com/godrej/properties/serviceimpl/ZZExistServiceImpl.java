package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ZZExistDao;
import com.godrej.properties.model.ZzholdTest;
import com.godrej.properties.service.ZZExistService;

@Service("zzExistService")
@Transactional
public class ZZExistServiceImpl implements ZZExistService{
	
	@Autowired
	private ZZExistDao dao;
	
	
	@Override
	public ZzholdTest unitExist(String unitNo) {
		// TODO Auto-generated method stub
		return dao.unitExist(unitNo);
	}
}
