package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.FloorWiseBooking;

public interface FloorWiseBookingDao { 
	List<FloorWiseBooking> getData(String towersfid, String projectsfid);
}