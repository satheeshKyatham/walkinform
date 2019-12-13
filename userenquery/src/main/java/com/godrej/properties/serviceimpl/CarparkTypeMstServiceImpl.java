package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.CarparkTypeMstDao;
import com.godrej.properties.model.CarparkTypeMst;
import com.godrej.properties.model.SchemePromotional;
import com.godrej.properties.service.CarparkTypeMstService;

@Service("carparkTypeMstService")
@Transactional
public class CarparkTypeMstServiceImpl implements CarparkTypeMstService {
	
	@Autowired
	CarparkTypeMstDao carparkTypeMstDao;
	

	@Override
	public List<CarparkTypeMst> getCarparkType(String projectSFID) {
		// TODO Auto-generated method stub
		return carparkTypeMstDao.getCarparkType(projectSFID);
	}
	
	@Override
	public void insertCarparkDtl(CarparkTypeMst carparkTypeMst) {
		carparkTypeMstDao.insertCarparkDtl(carparkTypeMst);
	}
}
