package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.EnqAndProjectDtlDao;
import com.godrej.properties.model.EnqAndProjectDtl;
import com.godrej.properties.service.EnqAndProjectDtlService;

@Service("enqAndProjectDtlService")
@Transactional
public class EnqAndProjectDtlServiceImpl implements EnqAndProjectDtlService{
	@Autowired
	EnqAndProjectDtlDao enqAndProjectDtlDao;
	
	@Override
	public List<EnqAndProjectDtl> getEnqAndProjectData(String enqId) {
		return enqAndProjectDtlDao.getEnqAndProjectData(enqId);
	}
}