package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ZZrequestProcessDao;
import com.godrej.properties.model.ZzholdTest;
import com.godrej.properties.service.ZZrequestProcessService;


@Service("zZrequestProcessService")
@Transactional
public class ZZrequestProcessServiceImpl implements ZZrequestProcessService {
	@Autowired
	private ZZrequestProcessDao dao;
	
	@Override
	public void zzupdateRqst(ZzholdTest action) {
		dao.zzupdateRqst(action);		
	}
}
