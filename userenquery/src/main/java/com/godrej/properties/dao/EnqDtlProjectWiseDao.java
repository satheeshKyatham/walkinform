package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqDtlProjectWise;

public interface EnqDtlProjectWiseDao {
	List<EnqDtlProjectWise> getEnqForAdminUnitHold(String enqName, String projectSFID);
}