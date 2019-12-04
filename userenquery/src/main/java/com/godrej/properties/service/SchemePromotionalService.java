package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.SchemePromotional;

public interface SchemePromotionalService {
	List<SchemePromotional> getSchemePromotional(String projectId, String cp_flag_yn);
	void insertSchemePromotional(SchemePromotional schemePromotional);
}