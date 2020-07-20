package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.BookingOnMap;

public interface BookingOnMapDao {
	List<BookingOnMap> getEnqDtl(String projectId);
}
