package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ApplicantDtlDao;
import com.godrej.properties.model.ApplicantDtl;
import com.godrej.properties.service.ApplicantDtlService;

@Service("applicantDtlService")
@Transactional
public class ApplicantDtlServiceImpl implements ApplicantDtlService{
	@Autowired
	ApplicantDtlDao applicantDtlDao;
	
	@Override
	public List<ApplicantDtl> getApplicantData(String applicationSfid) {
		return applicantDtlDao.getApplicantData(applicationSfid);
	}
}
