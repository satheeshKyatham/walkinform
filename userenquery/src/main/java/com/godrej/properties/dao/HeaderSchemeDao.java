package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.HeaderScheme;

public interface HeaderSchemeDao {
	
	List<HeaderScheme> getHeaderSchemeChargs(String projectId);

}
