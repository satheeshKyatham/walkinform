package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.SchemeMapping;

public interface SchemeMappingDao {
	
	SchemeMapping getMappingDtl (int schemeID);
	
	void insertSchemeMapping(SchemeMapping schemeMapping);
	
	void insertBulkMapping (List<SchemeMapping> mapping);
}