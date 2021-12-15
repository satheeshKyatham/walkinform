package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.HoldParkingAdmin;
import com.godrej.properties.model.Parking;
import com.godrej.properties.model.ParkingAdmin;
import com.godrej.properties.model.ParkingRec;

public interface ParkingService {
	List<Parking> getParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String parkingTypeCP);
	
	List<ParkingAdmin> getAdminParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String searchadminparking, String parkingTypeCP);
	
	String parkingHold (String propid, String parkingsfid, String projectsfid, Integer userid);
	
	
	void holdInventoryAdmin(String projectId, String userId, String unitsfid, String holdmsg, String reasonInput, String flatsfid);
	
	void updateHoldParkingAdmin(HoldParkingAdmin parkingAdmin);
	
	List<Parking> getLocation(String projectsfid);
	
	Boolean getSalesParkingHold(String parkingsfid, String userid);
	
	String updateParkingStatus(String parkingsfid);
	
	List<ParkingRec> getParking(String parkingsfid, String projectsfid);
}