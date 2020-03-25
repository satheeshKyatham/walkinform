package com.godrej.properties.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.GeneratePaymentDao;
import com.godrej.properties.model.GeneratePayment;
import com.godrej.properties.service.GeneratePaymentService;

@Service("generatePaymentService")
@Transactional
public class GeneratePaymentServiceImpl implements GeneratePaymentService{
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private GeneratePaymentDao dao;

	@Override
	public void insertPaymentDtl(List<GeneratePayment> pymtDtl) {
		dao.insertPaymentDtl(pymtDtl);
	}
	
	@Override
	public List<GeneratePayment> getPaymentRecord(String enqSfid,String projectid) {
		return dao.getPaymentRecord(enqSfid,projectid);
	}
}