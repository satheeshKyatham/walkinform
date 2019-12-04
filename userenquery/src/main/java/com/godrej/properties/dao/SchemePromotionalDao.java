package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.SchemePromotional;

public interface SchemePromotionalDao {
	List<SchemePromotional> getSchemePromotional(String projectId, String cp_flag_yn);
	void insertSchemePromotional(SchemePromotional schemePromotional);
}