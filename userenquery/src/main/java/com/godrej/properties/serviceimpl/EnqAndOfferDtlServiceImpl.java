package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqAndOfferDtlDao;
import com.godrej.properties.model.EnqAndOfferDtl;
import com.godrej.properties.service.EnqAndOfferDtlService;

@Service("enqAndOfferDtlService")
@Transactional
public class EnqAndOfferDtlServiceImpl implements EnqAndOfferDtlService{
	@Autowired
	EnqAndOfferDtlDao enqAndOfferDtlDao;
	
	@Override
	public List<EnqAndOfferDtl> getEnqAndOfferData(String enqId, String offerSfid) {
		return enqAndOfferDtlDao.getEnqAndOfferData(enqId, offerSfid);
	}
}