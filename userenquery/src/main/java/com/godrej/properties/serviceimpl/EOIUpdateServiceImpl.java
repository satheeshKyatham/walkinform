package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIUpdateDao;
import com.godrej.properties.model.EOIRecords;
import com.godrej.properties.model.Enquiry;
import com.godrej.properties.service.EOIUpdateService;

@Service("eOIUpdateService")
@Transactional
public class EOIUpdateServiceImpl implements EOIUpdateService{
	@Autowired
	private EOIUpdateDao dao;
	
	@Override
	public void updateEOI(List<Enquiry> eoiRec) {
		  dao.updateEOI(eoiRec);
	}

	
}