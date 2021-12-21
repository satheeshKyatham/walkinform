package com.godrej.properties.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.HoldParkingEntryDao;
import com.godrej.properties.model.HoldParkingEntry;
import com.godrej.properties.service.HoldParkingEntryService;

@Service("holdParkingEntryService")
@Transactional
public class HoldParkingEntryServiceImpl implements HoldParkingEntryService{
	
	@Autowired
	private HoldParkingEntryDao dao;
	
	@Autowired
	HoldParkingEntryDao holdParkingEntryDao;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void insertHoldRqst(HoldParkingEntry action) {
		dao.insertHoldRqst(action);		
	}
	
	@Override
	public void updateForelease(HoldParkingEntry action) {
		holdParkingEntryDao.updateForelease(action);		
	}
	
	@Override
	public int getCurrentVersion(String parkingsfid) {
		return holdParkingEntryDao.getCurrentVersion(parkingsfid);
	}
	
	@Override
	public void updateParkingPreviousHold(HoldParkingEntry action) {
		dao.updateParkingPreviousHold(action);		
	}
	
	@Override
	public int releaseInventory(String parkingsfid, Integer userid, String flatsfid) {	
		try{
				HoldParkingEntry action = new HoldParkingEntry();
				
				if (flatsfid.trim().length() > 0) {
					action.setFlatsfid(flatsfid);
				} else {
					action.setFlatsfid(null);
				}
				//Integer version = getCurrentVersion(parkingsfid)+1;
				//action.setParkingsfid(parkingsfid);
				action.setStatusai("I");
				action.setHoldstatusyn("N");
				//action.setVersion(version);	
				action.setUser_id(userid);	
				action.setProject_id(parkingsfid);
				
				updateForelease(action);
				
		}catch (Exception e) {
			log.error("Update For Closing Manager Release -  Error - ", e);
		}
		
		return 0;
	}
}