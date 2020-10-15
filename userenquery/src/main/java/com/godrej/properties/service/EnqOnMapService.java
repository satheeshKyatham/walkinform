package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqOnMap;

public interface EnqOnMapService {
	List<EnqOnMap> getEnqDtl(String projectId, String finalVerticales);
}
