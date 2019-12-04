package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.BSPUpdateDao;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.BSPUpdateService;

@Service("bSPUpdateService")
@Transactional

public class BSPUpdateServiceImpl implements BSPUpdateService{
	@Autowired
	private BSPUpdateDao dao;

	@Override
	public void updateBSP(UnitDtl bsp) {
		 dao.updateBSP(bsp);
	}
}
