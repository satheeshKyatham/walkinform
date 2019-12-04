package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.CostSheetExistsDao;
import com.godrej.properties.model.CostSheet;
import com.godrej.properties.model.ExtraCharges;
import com.godrej.properties.service.CostSheetExistsService;

@Service("costSheetExistsService")
@Transactional
public class CostSheetExistsServiceImpl implements CostSheetExistsService{

	@Autowired
	private CostSheetExistsDao dao;
	
	@Override
	public CostSheet getCSData(String contactNo) {
		// TODO Auto-generated method stub
		return dao.getCSData(contactNo);
	}
	
	@Override
	public void updateCostSheet(List<CostSheet> charges) {
		  dao.updateCostSheet(charges);
		
	}
}
