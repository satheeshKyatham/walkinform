package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.ParkingReportDao;
import com.godrej.properties.model.ParkingReport;
import com.godrej.properties.service.ParkingReportService;

@Service("parkingReportService")
@Transactional
public class ParkingReportServiceImpl implements ParkingReportService{
	@Autowired
	ParkingReportDao  parkingReportDao;

	@Override
	public List<ParkingReport> getParkingReportDtl(String whereCondition) {
		return parkingReportDao.getParkingReportDtl(whereCondition);
	}
}