package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.SchemeSite;
import com.godrej.properties.model.SchemeSource;

public interface SchemeSiteDao {
	List<SchemeSite> getSchemeSite(String projectId, String cp_flag_yn);
	
	void insertSchemeSite(SchemeSite schemeSite);
}