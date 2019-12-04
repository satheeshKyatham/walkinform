package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.HoldIntervalDao;
import com.godrej.properties.model.Inventory;
import com.godrej.properties.service.HoldIntervalService;

@Service("holdIntervalService")
@Transactional
public class HoldIntervalServiceImpl implements HoldIntervalService{
	
	@Autowired
	private HoldIntervalDao dao;
	
	
	@Override
	public Inventory unitExist(String unitSfid, String projectNameId, String towerCode) {
		return dao.unitExist(unitSfid, projectNameId, towerCode);
	}


	@Override
	public Inventory getHeldUnit(String unitSfid) {
		return dao.getHeldUnit(unitSfid);
	}
		
	
}
