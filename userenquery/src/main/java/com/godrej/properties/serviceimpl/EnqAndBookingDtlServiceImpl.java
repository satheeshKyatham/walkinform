package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqAndBookingDtlDao;
import com.godrej.properties.model.EnqAndBookingDtl;
import com.godrej.properties.service.EnqAndBookingDtlService;

@Service("enqAndBookingDtlService")
@Transactional
public class EnqAndBookingDtlServiceImpl implements EnqAndBookingDtlService{
	@Autowired
	EnqAndBookingDtlDao enqAndBookingDtlDao;
	
	@Override
	public List<EnqAndBookingDtl> getEnqAndBookingData(String enqId, String applicationBookingId) {
		return enqAndBookingDtlDao.getEnqAndBookingData(enqId, applicationBookingId);
	}
}
