package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.model.SchemeMapping;

public interface SchemeMappingService {
	SchemeMapping getMappingDtl (int schemeID);
	
	void insertSchemeMapping(SchemeMapping schemeMapping);
	
	void insertBulkMapping(List<SchemeMapping> mapping);
}