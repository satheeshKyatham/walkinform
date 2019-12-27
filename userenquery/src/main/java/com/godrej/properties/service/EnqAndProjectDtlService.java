package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqAndProjectDtl;

public interface EnqAndProjectDtlService {
	List<EnqAndProjectDtl> getEnqAndProjectData(String enqId);
}