package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.ApplicantDtl;

public interface ApplicantDtlDao {
	List<ApplicantDtl> getApplicantData(String applicationSfid);
}
