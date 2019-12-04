package com.godrej.properties.service;

import java.util.List;

import com.godrej.properties.model.EnqAndOfferDtl;

public interface EnqAndOfferDtlService {
	List<EnqAndOfferDtl> getEnqAndOfferData(String enqId, String offerSfid);
}