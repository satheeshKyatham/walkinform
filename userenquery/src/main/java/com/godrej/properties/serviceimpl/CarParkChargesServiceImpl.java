package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CarParkChargesDao;
import com.godrej.properties.model.CarParkCharges;
import com.godrej.properties.service.CarParkChargesService;

@Service("carParkChargesService")
@Transactional
public class CarParkChargesServiceImpl implements CarParkChargesService{
	
	@Autowired
	private CarParkChargesDao dao;
	
	@Autowired
	CarParkChargesDao carParkChargesDao;
	
	public void insertCPAmount(CarParkCharges carParkCharges) {
		dao.insertCPAmount(carParkCharges);
	}
	
	@Override
	public CarParkCharges getCharges(String parkType, String projectSFID) {
		// TODO Auto-generated method stub
		return dao.getCharges(parkType, projectSFID);
	}
	
}
