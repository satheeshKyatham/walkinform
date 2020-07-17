package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.BookingOnMap;

public interface BookingOnMapService {
	List<BookingOnMap> getEnqDtl(String projectId);
}
