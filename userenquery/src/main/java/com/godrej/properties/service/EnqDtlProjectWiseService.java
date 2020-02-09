package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqDtlProjectWise;

public interface EnqDtlProjectWiseService {
	List<EnqDtlProjectWise> getEnqForAdminUnitHold(String enqName, String projectSFID);
}