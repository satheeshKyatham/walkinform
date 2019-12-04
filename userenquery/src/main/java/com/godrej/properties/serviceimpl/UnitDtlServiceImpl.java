package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.UnitDtlDao;
import com.godrej.properties.model.UnitDtl;
import com.godrej.properties.service.UnitDtlService;

@Service("unitDtlService")
@Transactional
public class UnitDtlServiceImpl implements UnitDtlService{

	@Autowired
	private UnitDtlDao dao;
	
	public void setUnitDtl(UnitDtl unitDtl) {
		dao.setUnitDtl(unitDtl);
	}

}
