package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqAndBookingDtl;

public interface EnqAndBookingDtlDao {
	List<EnqAndBookingDtl> getEnqAndBookingData(String enqId, String applicationBookingId);
}
