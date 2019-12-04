package com.godrej.properties.dao;

import java.util.List;

import com.godrej.properties.model.EnqAndBookingDtl;
import com.godrej.properties.model.EnqAndOfferDtl;

public interface EnqAndOfferDtlDao {
	List<EnqAndOfferDtl> getEnqAndOfferData(String enqId, String offerSfid);
}
