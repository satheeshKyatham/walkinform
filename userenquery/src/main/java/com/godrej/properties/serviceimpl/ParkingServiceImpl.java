package com.godrej.properties.serviceimpl;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.HoldParkingEntryDao;
import com.godrej.properties.dao.ParkingDao;
import com.godrej.properties.dao.SalesUnitHoldStatusDao;
import com.godrej.properties.dto.HoldParkingAdminDao;
import com.godrej.properties.model.HoldInventoryEntry;
import com.godrej.properties.model.HoldParkingAdmin;
import com.godrej.properties.model.HoldParkingEntry;
import com.godrej.properties.model.Parking;
import com.godrej.properties.model.ParkingAdmin;
import com.godrej.properties.model.ParkingRec;
import com.godrej.properties.service.ParkingService;

@Service("parkingService")
@Transactional
public class ParkingServiceImpl implements ParkingService {
	
	@Autowired
	ParkingDao parkingDao;
	
	@Autowired
	SalesUnitHoldStatusDao salesUnitHoldStatusDao;
	
	@Autowired
	HoldParkingEntryDao holdParkingEntryDao;
	
	@Autowired
	HoldParkingAdminDao holdParkingAdminDao;
	
	
	@Override
	public List<Parking> getParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String parkingTypeCP) {
		return parkingDao.getParkingDtl(propertyTypeSfid, projectId, towerMst, unitCategory, parkingLocation, parkingTypeCP);
	}
	
	@Override
	public List<ParkingAdmin> getAdminParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String searchadminparking, String parkingTypeCP) {
		return parkingDao.getAdminParkingDtl(propertyTypeSfid, projectId, towerMst, unitCategory, parkingLocation, searchadminparking, parkingTypeCP);
	}
	
	public void saveHoldInventoryAdmin(HoldParkingAdmin parkingAdmin) {
		holdParkingAdminDao.saveHoldInventoryAdmin(parkingAdmin);
	}
	
	@Override
	public void updateHoldParkingAdmin(HoldParkingAdmin parkingAdmin) {
		holdParkingAdminDao.updateHoldParkingAdmin(parkingAdmin);
	}
	
	@Override
	public String parkingHold(String propid, String parkingsfid, String projectsfid, Integer userid) {
		
		String result = "";
		
		HoldInventoryEntry salesUnitStatus = salesUnitHoldStatusDao.getUnitHoldDtl(propid);
		
		if (salesUnitStatus == null) {
			result = "{\"status\":\"STATUS_OK\",\"record_found\":\"NO\", \"errorid\": \"E1\",\"error_msg\":\"Flat/Unit hold record not found\"}";
			return result;
		}

		Timestamp currentTpm = new Timestamp(System.currentTimeMillis());

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentTpm.getTime());

		// add a bunch of seconds to the calendar
		cal.add(Calendar.SECOND, 98765);

		Timestamp createdAt  = salesUnitStatus.getCreated_at();
		long time = createdAt.getTime();
		// create a second time stamp
		int holdTime =  salesUnitStatus.getHoldForTime();
		Timestamp timestampValue = new Timestamp(time+ holdTime);

		long milliseconds = timestampValue.getTime() - currentTpm.getTime();
		
		int seconds = (int) milliseconds / 1000;
		int hours = seconds / 3600;
		int minutes = (int) ((milliseconds / 1000)  / 60);
		seconds = (seconds % 3600) % 60;
		
		//Insert Entry
		//log.info("Unit Exit Query No Entry found************************* HOLD Issue");
		HoldParkingEntry action = new HoldParkingEntry();
		action.setParkingsfid(parkingsfid);
		action.setHoldstatusyn("Y");
		action.setStatusai("A");
		action.setProject_id(projectsfid);
		action.setUser_id(userid);
		action.setHoldForTime(milliseconds); 
		action.setFlatsfid(propid);
		action.setCreated_at(new Timestamp(System.currentTimeMillis()));
		//log.info("Before Insert************************* HOLD Issue");

		
		
		//New
		Parking parkingDtl = parkingDao.getHeldUnit(parkingsfid);
		
		if (parkingDtl != null && !parkingDtl.equals("")) {
			result = "{\"status\":\"STATUS_NOTOK\",\"record_found\":\"NO\", \"errorid\": \"E2\",\"error_msg\":\"Sorry this parking is Held by someone else, Please Try again after some time.\"}";
		} else {
			holdParkingEntryDao.insertHoldRqst(action);
			result = "{\"status\":\"STATUS_OK\",\"record_found\":\"YES\", \"errorid\": \"-\",\"error_msg\":\"Parking successfully held\"}";
		}
		
		return result;
		//END New
		
		
		//try {
		//Boolean isParkingHold = holdParkingEntryDao.insertHoldRqst(action);
	
		/*if (isParkingHold) {
			result = "{\"status\":\"STATUS_OK\",\"record_found\":\"YES\", \"errorid\": \"-\",\"error_msg\":\"Parking successfully held\"}";
		} else {
			result = "{\"status\":\"STATUS_NOTOK\",\"record_found\":\"NO\", \"errorid\": \"E2\",\"error_msg\":\"Selected parking is no longer available, please select another parking.\"}";
		}*/
		
		
		//} catch (Exception e) {
			//result = "{\"status\":\"STATUS_NOTOK\",\"record_found\":\"NO\", \"errorid\": \"E2\",\"error_msg\":\"Selected parking is no longer available please select another parking.\"}";
			//log.error("Exception while holding inventory " + e);
			//return gson.toJson("Sorry this unit is Held by someone else, Please Try again after some time");
		//}
		//log.info("After Insert************************* HOLD Issue");
		
		//return result;
		//END Insert Entry
	}
	
	
	@Override
	public void holdInventoryAdmin(String projectId, String userId, String unitsfid, String holdmsg, String reasonInput, String flatsfid)
	{
		boolean eoiHoldStatus = false;	
		
		if (holdmsg.equals("eoi_block")) {
			eoiHoldStatus = true;
			holdmsg = "temp";
		} else {
			eoiHoldStatus = false;
		}
			
		 String [] data= unitsfid.split(",");
		 
		 
		 for (int i=0;i<data.length;i++){
			 HoldParkingAdmin parkingAdmin= new HoldParkingAdmin();
			 
			 parkingAdmin.setFlatsfid(flatsfid);
			 parkingAdmin.setParkingsfid(data[i]);
			 parkingAdmin.setProjectsfid(projectId);
			 parkingAdmin.setCreated_at(new Timestamp(new Date ().getTime()));
			 parkingAdmin.setCreated_by(userId);
			 parkingAdmin.setHold_reason(holdmsg);
			 parkingAdmin.setHold_status(true);
			 parkingAdmin.setHold_description(reasonInput);
			 
			 saveHoldInventoryAdmin(parkingAdmin);
			 //-------------------------
		 } 
	}
	
	@Override
	public List<Parking> getLocation(String projectsfid) {
		return parkingDao.getLocation(projectsfid);
	}
	
	
	@Override
	public Boolean getSalesParkingHold(String parkingsfid, String userid) {
		return holdParkingEntryDao.getSalesParkingHold(parkingsfid, userid);
	}
	
	@Override
	public String updateParkingStatus(String parkingsfid) {
		return parkingDao.updateParkingStatus(parkingsfid);
	}
	
	@Override
	public List<ParkingRec> getParking(String parkingsfid, String projectsfid) {
		return parkingDao.getParking(parkingsfid, projectsfid);
	}
}