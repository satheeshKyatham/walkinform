package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.WithoutOtherChargesPPDao;
import com.godrej.properties.model.WithoutOtherChargesPP;
import com.godrej.properties.service.WithoutOtherChargesPPService;

@Service("withoutOtherChargesPPService")
@Transactional

public class WithoutOtherChargesPPServiceImpl implements  WithoutOtherChargesPPService{
	@Autowired
	private WithoutOtherChargesPPDao withoutOtherChargesPPDao;
	
	@Override
	public List<WithoutOtherChargesPP> getPPData(String paymentPlanSfid) {
		// TODO Auto-generated method stub
		return withoutOtherChargesPPDao.getPPData(paymentPlanSfid);
	}
}
