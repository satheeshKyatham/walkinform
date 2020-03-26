package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.EnqJourneyDtlDao;
import com.godrej.properties.model.EnqJourneyDtl;
import com.godrej.properties.service.EnqJourneyDtlService; 


@Service("enqJourneyDtlService")
@Transactional
public class EnqJourneyDtlServiceImpl implements EnqJourneyDtlService {

	@Autowired
	EnqJourneyDtlDao enqJourneyDtlDao;
	 
	@Override
	public List<EnqJourneyDtl> getAllData(String projectid,String enquiryid) {
		return enqJourneyDtlDao.getAllData(projectid,enquiryid);
	}
}