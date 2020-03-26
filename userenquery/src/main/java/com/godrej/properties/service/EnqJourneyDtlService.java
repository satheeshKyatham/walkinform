package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqJourneyDtl;

public interface EnqJourneyDtlService {
	List<EnqJourneyDtl> getAllData(String projectid,String enquiryid);
}