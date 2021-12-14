package com.godrej.properties.daoimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarParkingMappingLogDao;
import com.godrej.properties.model.CarParkingMappingLog;

@Repository("carparkingMappingLogDao")
public class CarParkingMappingLogDaoImpl extends AbstractDao<Integer, CarParkingMappingLog> implements CarParkingMappingLogDao{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public CarParkingMappingLog insertCarParkingCombination(CarParkingMappingLog carParkingMappingLog) {
		persist(carParkingMappingLog);
		return carParkingMappingLog;
	}

}
