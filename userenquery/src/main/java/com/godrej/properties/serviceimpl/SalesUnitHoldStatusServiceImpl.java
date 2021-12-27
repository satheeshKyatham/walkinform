package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SalesUnitHoldStatusDao;
import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.service.SalesUnitHoldStatusService;

@Service("salesUnitHoldStatusService")
@Transactional
public class SalesUnitHoldStatusServiceImpl implements SalesUnitHoldStatusService{
	@Autowired
	SalesUnitHoldStatusDao salesUnitHoldStatusDao;
	
	@Override
	public Boolean getSalesUnitHold(String unitSFID, String userid) {
		return salesUnitHoldStatusDao.getSalesUnitHold(unitSFID, userid);
	}
	
	@Override
	public HoldInventoryEntry getUnitHoldDtl(String unitSFID, Integer userid) {
		return salesUnitHoldStatusDao.getUnitHoldDtl(unitSFID, userid);
	}
}