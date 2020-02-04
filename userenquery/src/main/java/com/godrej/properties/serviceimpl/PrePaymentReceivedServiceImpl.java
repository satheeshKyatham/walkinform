package com.godrej.properties.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.PrePaymentReceivedDao;
import com.godrej.properties.model.PrePaymentReceived;
import com.godrej.properties.service.PrePaymentReceivedService;

@Service("prePaymentService")
@Transactional
public class PrePaymentReceivedServiceImpl implements PrePaymentReceivedService {

	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PrePaymentReceivedDao prePaymentDao;
	
	@Override
	public List<PrePaymentReceived> getPrePaymentDetails(String whereCondition) {
		// TODO Auto-generated method stub
		return prePaymentDao.getPrePaymentDetails(whereCondition);
	}

}
