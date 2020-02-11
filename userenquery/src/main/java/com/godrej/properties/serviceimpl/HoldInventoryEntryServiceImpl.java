package com.godrej.properties.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void insertHoldRqst(HoldInventoryEntry action) {
		dao.insertHoldRqst(action);		
	}
	
	
	@Override
	public HoldInventoryEntry holdExist(String projectNameId, String customerId) {
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
		return holdInventoryEntryDao.holdDataExist(projectNameId, customerId);
	}


	@Override
	public int getCurrentVersion(String projectId, String unitId, String customerId) {
	
		
		return dao.getCurrentVersion(projectId, unitId, customerId);
	}
	
	@Override
	public int releaseInventory(String projectSfid, String unitSfid, String customerId) {	
		try{
				HoldInventoryEntry action = new HoldInventoryEntry();
		
				Integer version = getCurrentVersion(projectSfid, unitSfid, customerId)+1;
				action.setUnitSfid(unitSfid);
				action.setCustomer_id(customerId);
				action.setProject_id(projectSfid);
				action.setStatusai("I");
				action.setHoldstatusyn("N");
				action.setVersion(version);	
				updateForelease(action);
		}catch (Exception e) {
			log.error("Error - ", e);
		}
		
		return 0;
	}
	
}