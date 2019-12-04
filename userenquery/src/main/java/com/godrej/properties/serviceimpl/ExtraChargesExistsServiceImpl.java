package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ExtraChargesExistsDao;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.service.ExtraChargesExistsService;

@Service("extraChargesExistsService")
@Transactional
public class ExtraChargesExistsServiceImpl implements ExtraChargesExistsService{

	@Autowired
	private ExtraChargesExistsDao dao;
	
	@Override
	public ExtraCharges getECData(String contactNo) {
		// TODO Auto-generated method stub
		return dao.getECData(contactNo);
	}

	@Override
	public void updateExtraCharges(List<ExtraCharges> charges) {
		  dao.updateExtraCharges(charges);
		
	}

}
