package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.Floor;


public interface FloorService {
	public List<Floor> getData(String towersfid);
}