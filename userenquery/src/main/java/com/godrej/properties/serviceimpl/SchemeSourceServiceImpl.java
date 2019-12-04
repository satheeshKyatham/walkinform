package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.SchemeSourceDao;
import com.godrej.properties.model.SchemeSource;
import com.godrej.properties.service.SchemeSourceService;

@Service("schemeSourceService")
@Transactional
public class SchemeSourceServiceImpl implements SchemeSourceService{
	
	@Autowired
	private SchemeSourceDao schemeSourceDao;
	
	@Override
	public List<SchemeSource> getSchemeSource(String projectId, String cp_flag_yn) {
		return schemeSourceDao.getSchemeSource(projectId, cp_flag_yn);
	}
	
	@Override
	public void insertSchemeSource(SchemeSource schemeSource) {
		schemeSourceDao.insertSchemeSource(schemeSource);
	}
}