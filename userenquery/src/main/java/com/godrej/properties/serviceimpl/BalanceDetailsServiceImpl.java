package com.godrej.properties.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.BalanceDetailsDao;
import com.godrej.properties.model.BalanceDetails;
import com.godrej.properties.service.BalanceDetailsService;

@Service("balanceDetailsService")
@Transactional
public class BalanceDetailsServiceImpl implements BalanceDetailsService{
	
	@Autowired
	private BalanceDetailsDao dao;
	
	@Autowired
	BalanceDetailsDao balanceDetailsDao;
	
	
	@Override
	public BalanceDetails insertBalanceDetails(BalanceDetails action) {
		return dao.insertBalanceDetails(action);		
	}


	@Override
	public List<BalanceDetails> getCreatedOffersList(String projectid) {
		return dao.getCreatedOffersList(projectid);
	}


	@Override
	public void eCRMCancelledOfferInactive() {
		dao.eCRMCancelledOfferInactive();
	}
	
}
