package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ApplicationDtl;

public interface ApplicationDtlService {
	List<ApplicationDtl> getApplicationDtl(String whereCondition);
}