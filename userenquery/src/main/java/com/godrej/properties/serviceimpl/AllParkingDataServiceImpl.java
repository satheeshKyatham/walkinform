package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.AllParkingDataDao;
import com.godrej.properties.model.AllParkingData;
import com.godrej.properties.service.AllParkingDataService;

@Service("allParkingReportService")
@Transactional
public class AllParkingDataServiceImpl implements AllParkingDataService{
	@Autowired
	AllParkingDataDao  allParkingDataDao;

	@Override
	public List<AllParkingData> getParkingReportDtl(String whereCondition) {
		return allParkingDataDao.getParkingReportDtl(whereCondition);
	}
}