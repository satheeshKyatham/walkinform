package com.godrej.properties.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqAndOfferDtlDao;
import com.godrej.properties.model.EnqAndOfferDtl;
import com.godrej.properties.model.InventoryOtherCharges;
import com.godrej.properties.service.EnqAndOfferDtlService;
import com.godrej.properties.service.InventoryOtherChargesService;

@Service("enqAndOfferDtlService")
@Transactional
public class EnqAndOfferDtlServiceImpl implements EnqAndOfferDtlService{
	@Autowired
	EnqAndOfferDtlDao enqAndOfferDtlDao;
	
	@Autowired
	private InventoryOtherChargesService otherChargesService;
	
	@Override
	public List<EnqAndOfferDtl> getEnqAndOfferData(String enqId, String offerSfid) {
		
		List<EnqAndOfferDtl> details = new ArrayList<>(); 
		
		List<EnqAndOfferDtl> enquiryDetails = enqAndOfferDtlDao.getEnqAndOfferData(enqId, offerSfid);
		
		if(enquiryDetails == null || enquiryDetails.isEmpty()) {
			return details;
		}
		EnqAndOfferDtl detail = enquiryDetails.get(0);
		Map<String, InventoryOtherCharges> otherCharges = otherChargesService.getOtherChargeMap(detail.getProp_sfid());
		detail.setMappedCharges(otherCharges);
		details.add(detail);
		return details;
	}
}