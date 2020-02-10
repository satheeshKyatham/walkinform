package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.HoldInventoryEntryDao;
import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.service.HoldInventoryEntryService;

@Service("holdInventoryEntryService")
@Transactional
public class HoldInventoryEntryServiceImpl implements HoldInventoryEntryService{
	
	
	
	
	@Autowired
	private HoldInventoryEntryDao dao;
	
	@Autowired
	HoldInventoryEntryDao holdInventoryEntryDao;
	
	
	@Override
	public void insertHoldRqst(HoldInventoryEntry action) {
		dao.insertHoldRqst(action);		
	}
	
	
	@Override
	public HoldInventoryEntry holdExist(String projectNameId, String customerId) {
		// TODO Auto-generated method stub
		return dao.holdExist(projectNameId, customerId);
	}
	
	/**
	 * @author Vivek Birdi
	 * get  Holding units by User
	 * @param userId - user id to get holdings.
	 */
	@Override
	public HoldInventoryEntry getHolding(int userId) {
		return dao.getHolding(userId);
	}
	
	@Override
	public void updateForelease(HoldInventoryEntry action) {
		dao.updateForelease(action);		
	}
	
	
	@Override
	public void updatePreviousHold(HoldInventoryEntry action) {
		dao.updatePreviousHold(action);		
	}
	
	
	
	@Override
	public List<HoldInventoryEntry> holdDataExist(String projectNameId, String customerId) {
		// TODO Auto-generated method stub
		return holdInventoryEntryDao.holdDataExist(projectNameId, customerId);
	}


	@Override
	public int getCurrentVersion(String projectId, String unitId, String customerId) {
	
		
		return dao.getCurrentVersion(projectId, unitId, customerId);
	}
	
}