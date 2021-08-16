package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.FloorWiseBooking;


public interface FloorWiseBookingService {
	public List<FloorWiseBooking> getData(String towersfid, String projectsfid);
}