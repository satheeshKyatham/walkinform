package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqAndBookingDtl;

public interface EnqAndBookingDtlService {
	List<EnqAndBookingDtl> getEnqAndBookingData(String enqId, String applicationBookingId);
}