package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Parking;
import com.godrej.properties.model.ParkingAdmin;
import com.godrej.properties.model.ParkingRec;

public interface ParkingDao {
	List<Parking> getParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation);
	
	List<ParkingAdmin> getAdminParkingDtl(String propertyTypeSfid, String projectId, String towerMst, String unitCategory, String parkingLocation, String searchadminparking);
	
	List<Parking> getLocation(String towersfid);
	
	String updateParkingStatus(String parkingsfid);
	
	public Parking getHeldUnit(String parkingsfid);
	
	List<ParkingRec> getParking(String parkingsfid, String projectsfid);
}