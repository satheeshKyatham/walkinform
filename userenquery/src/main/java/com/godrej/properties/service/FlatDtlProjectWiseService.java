package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.FlatDtlProjectWise;

public interface FlatDtlProjectWiseService {
	List<FlatDtlProjectWise> getFlatForAdminUnitHold(String flatName, String projectSFID);
}