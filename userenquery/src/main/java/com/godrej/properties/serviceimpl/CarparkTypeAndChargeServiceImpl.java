package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CarparkTypeAndChargeDao;
import com.godrej.properties.model.CarparkCount;
import com.godrej.properties.model.CarparkTypeAndCharge;
import com.godrej.properties.service.CarparkTypeAndChargeService;

@Service("carparkTypeAndChargeService")
@Transactional
public class CarparkTypeAndChargeServiceImpl implements CarparkTypeAndChargeService {
	
	@Autowired
	CarparkTypeAndChargeDao carparkTypeAndChargeDao;
	

	@Override
	public List<CarparkTypeAndCharge> getCarparkType(String projectSFID) {
		// TODO Auto-generated method stub
		return carparkTypeAndChargeDao.getCarparkType(projectSFID);
	}	
	
	@Override
	public List<CarparkCount> getCarparkCount(String projectSFID) {
		return carparkTypeAndChargeDao.getCarparkCount(projectSFID);
	}
}