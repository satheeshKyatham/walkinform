package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ExtraChargesDao;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.service.ExtraChargesService;

@Service("extraChargesService")
@Transactional
public class ExtraChargesServiceImpl implements ExtraChargesService{

	@Autowired
	private ExtraChargesDao dao;

	@Override
	public void setExtraChrgs(List<ExtraCharges> charges, String equery_id, String contactNo) {
		dao.setExtraChrgs(charges, equery_id, contactNo);
		
	}
	
}
