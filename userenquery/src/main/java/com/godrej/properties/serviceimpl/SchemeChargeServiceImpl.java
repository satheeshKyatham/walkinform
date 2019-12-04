package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.OtherChargesDao;
import com.godrej.properties.dao.SchemeChargeDao;
import com.godrej.properties.model.Scheme;
import com.godrej.properties.service.SchemeChargeService;

@Service("schemeChargeService")
@Transactional
public class SchemeChargeServiceImpl implements SchemeChargeService{

	@Autowired
	private SchemeChargeDao schemeChargesDao;
	
	@Override
	public void insertSchemeCharge(Scheme schemeCharges) {
		schemeChargesDao.insertSchemeCharge(schemeCharges);
	}

	@Override
	public List<Scheme> getSchemeChargs(int schemeSource, int schemeSite, int schemePromotional, String projectId, String cp_flag_yn) {
		// TODO Auto-generated method stub
		return schemeChargesDao.getSchemeChargs(schemeSource,  schemeSite,  schemePromotional, projectId, cp_flag_yn);
	}

}
