package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.EOIReportDao;
import com.godrej.properties.model.EOIReport;
import com.godrej.properties.service.EOIReportService;

@Service("eOIReportService")
@Transactional
public class EOIReportServiceImpl implements EOIReportService{
	@Autowired
	EOIReportDao  eOIReportDao;

	@Override
	public List<EOIReport> getEOIReportDtl(String whereCondition) {
		// TODO Auto-generated method stub
		return eOIReportDao.getEOIReportDtl(whereCondition);
	}
}