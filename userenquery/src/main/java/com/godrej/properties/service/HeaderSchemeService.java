package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.HeaderScheme;

public interface HeaderSchemeService {
	List<HeaderScheme> getHeaderSchemeChargs(String projectid);
}