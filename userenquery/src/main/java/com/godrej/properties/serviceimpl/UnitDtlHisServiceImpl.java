package com.godrej.properties.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.UnitDtlHisDao;
import com.godrej.properties.model.UnitDtlHis;
import com.godrej.properties.service.UnitDtlHisService;

@Service("unitDtlHisService")
@Transactional
public class UnitDtlHisServiceImpl implements UnitDtlHisService{

	@Autowired
	private UnitDtlHisDao dao;
	
	public void setUnitDtl(UnitDtlHis unitDtlHis) {
		dao.setUnitDtl(unitDtlHis);
	}

}
