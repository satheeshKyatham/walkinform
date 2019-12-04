package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EOIPreferenceDtlDao;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.service.EOIPreferenceDtlService;

@Service("eOIPreferenceDtlService")
@Transactional
public class EOIPreferenceDtlServiceImpl implements EOIPreferenceDtlService{
	@Autowired
	private EOIPreferenceDtlDao dao;

	@Override
	public void insertEOI(List<EOIPreferenceDtl> eoiDtl) {
		dao.insertEOI(eoiDtl);
	}
	
	@Override
	public List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid) {
		return dao.getEOIPreferenceRecord(enqSfid);
	}
}