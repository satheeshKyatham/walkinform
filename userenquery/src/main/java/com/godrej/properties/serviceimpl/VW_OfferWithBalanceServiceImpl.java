package com.godrej.properties.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.godrej.properties.dao.VW_OfferWithBalanceDao;
import com.godrej.properties.model.Vw_OfferWithBalance;
import com.godrej.properties.service.VW_OfferWithBalanceService;

@Service("vW_OfferWithBalanceService")
@Transactional
public class VW_OfferWithBalanceServiceImpl implements VW_OfferWithBalanceService{
	
	@Autowired
	VW_OfferWithBalanceDao vw_OfferWithBalanceDao;

	@Override
	public List<Vw_OfferWithBalance> getOfferList(String whereCondition) {
		// TODO Auto-generated method stub
		return vw_OfferWithBalanceDao.getOfferListList(whereCondition);
	}

}
