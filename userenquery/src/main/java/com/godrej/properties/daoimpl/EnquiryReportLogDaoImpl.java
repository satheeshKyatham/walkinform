package com.godrej.properties.daoimpl;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.EnquiryReportLogDao;
import com.godrej.properties.model.EnquiryReport;
import com.godrej.properties.model.EnquiryReportLog;

@Repository
public class EnquiryReportLogDaoImpl extends AAbstractDao<EnquiryReportLog> implements EnquiryReportLogDao{

	@Override
	public EnquiryReportLog insertEnquiryReportLog(EnquiryReportLog enq) {
		persist(enq);
		return enq;
	}

}
