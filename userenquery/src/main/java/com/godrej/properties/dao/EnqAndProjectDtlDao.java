package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqAndProjectDtl;

public interface EnqAndProjectDtlDao {
	List<EnqAndProjectDtl> getEnqAndProjectData(String enqId);
}