package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.ExtraChargesHisDao;
import com.godrej.properties.model.ExtraChargesHis;
import com.godrej.properties.service.ExtraChargesHisService;

@Service("extraChargesHisService")
@Transactional
public class ExtraChargesHisServiceImpl implements ExtraChargesHisService{

	@Autowired
	private ExtraChargesHisDao dao;

	@Override
	public void setExtraChrgs(List<ExtraChargesHis> charges, String equery_id, String contactNo) {
		dao.setExtraChrgs(charges, equery_id, contactNo);
		
	}
	
}
