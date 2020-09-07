package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CostsheetReportDao;
import com.godrej.properties.model.CostsheetReport;
import com.godrej.properties.service.CostsheetReportService;

@Service("costsheetReportService")
@Transactional
public class CostsheetReportServiceImpl implements CostsheetReportService{
	@Autowired
	CostsheetReportDao  costsheetReportDao;

	@Override
	public List<CostsheetReport> getReportDtl(String whereCondition) {
		return costsheetReportDao.getReportDtl(whereCondition);
	}
}