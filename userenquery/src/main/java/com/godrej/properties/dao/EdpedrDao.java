package com.godrej.properties.dao;

public interface EdpedrDao { 
	Integer updateData(String multiFloor, String projectsfid, String edpDate);
	Integer updateEDRData(String bookingsfid, String projectsfid, String edpDate);
	
	Boolean insertData(String multiFloor, String projectsfid, String edpDate, String userid, String tower);
	Boolean insertDataEDR(String selectedFloor, String projectsfid, String edrDate, String userid, String selectedTower, String bookingsfid);
}