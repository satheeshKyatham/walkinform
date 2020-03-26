package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqJourneyDtl;

public interface EnqJourneyDtlDao {
	List<EnqJourneyDtl> getAllData(String projectid,String enquiryid); 
}