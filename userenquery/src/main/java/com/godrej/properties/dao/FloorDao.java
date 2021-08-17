package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.Floor;

public interface FloorDao { 
	List<Floor> getData(String towersfid);
}