package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.UnitExistsDao;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.UnitExistsService;

@Service("unitExistsService")
@Transactional
public class UnitExistsServiceImpl implements UnitExistsService{

	@Autowired
	private UnitExistsDao dao;
	
	@Override
	public UnitDtl getUnitData(String contactNo) {
		// TODO Auto-generated method stub
		return dao.getUnitData(contactNo);
	}
	
	@Override
	public UnitDtl unitExist(String unitNo) {
		// TODO Auto-generated method stub
		return dao.unitExist(unitNo);
	}

	@Override
	public void updateUnitDtl(UnitDtl uDtl) {
		dao.updateUnitDtl(uDtl);		
	}

}
