package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.FlatDtlProjectWise;

public interface FlatDtlProjectWiseDao {
	List<FlatDtlProjectWise> getFlatForAdminUnitHold(String flatName, String projectSFID);
}