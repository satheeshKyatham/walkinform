package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.ApplicationDtlDao;
import com.godrej.properties.model.ApplicationDtl;
import com.godrej.properties.service.ApplicationDtlService;

@Service("applicationDtlService")
@Transactional
public class ApplicationDtlServiceImpl implements ApplicationDtlService{
	@Autowired
	 ApplicationDtlDao  applicationDtlDao;

	@Override
	public List<ApplicationDtl> getApplicationDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return applicationDtlDao.getApplicationDtl(whereCondition);
	}
}