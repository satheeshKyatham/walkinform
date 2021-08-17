package com.godrej.properties.service;

public interface EdpedrService {
	public Integer updateData(String multiFloor, String projectsfid, String edpDate);
	
	public Integer updateEDRData(String bookingsfid, String projectsfid, String edpDate);
	
	public Boolean insertData(String multiFloor, String projectsfid, String edpDate, String userid, String tower);
	public Boolean insertDataEDR(String selectedFloor, String projectsfid, String edrDate, String userid, String selectedTower, String bookingsfid);
}