package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.ApplicantDtl;

public interface ApplicantDtlService {
	List<ApplicantDtl> getApplicantData(String applicationSfid);
}
