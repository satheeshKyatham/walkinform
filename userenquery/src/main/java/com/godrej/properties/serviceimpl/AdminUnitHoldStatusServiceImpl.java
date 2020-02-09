package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AdminUnitHoldStatusDao;
import com.godrej.properties.service.AdminUnitHoldStatusService;

@Service("adminUnitHoldStatusService")
@Transactional
public class AdminUnitHoldStatusServiceImpl implements AdminUnitHoldStatusService{
	@Autowired
	AdminUnitHoldStatusDao adminUnitHoldStatusDao;
	
	/*
	 * @Override public List<HoldInventoryAdmin> getAdminUnitHold(String unitSFID) {
	 * return adminUnitHoldStatusDao.getAdminUnitHold(unitSFID); }
	 */
	
	@Override
	public Boolean getAdminUnitHold(String unitSFID) {
		return adminUnitHoldStatusDao.getAdminUnitHold(unitSFID);
	}
	
	
}