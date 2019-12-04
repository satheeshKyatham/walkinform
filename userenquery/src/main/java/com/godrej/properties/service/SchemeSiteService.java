package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.SchemeSite;

public interface SchemeSiteService {
	List<SchemeSite> getSchemeSite(String projectId, String cp_flag_yn);
	
	void insertSchemeSite(SchemeSite schemeSite);
}