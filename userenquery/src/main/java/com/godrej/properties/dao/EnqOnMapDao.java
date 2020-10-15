package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqOnMap;

public interface EnqOnMapDao {
	List<EnqOnMap> getEnqDtl(String projectId, String finalVerticales);
}
