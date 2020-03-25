package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.GeneratePaymentDao;
import com.godrej.properties.model.GeneratePayment;

@SuppressWarnings("unchecked")
@Repository("generatePaymentDao")
public class GeneratePaymentDaoImpl extends AbstractDao<Integer, GeneratePayment> implements GeneratePaymentDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertPaymentDtl(List<GeneratePayment> pymtDtl) {
		batchPersist(pymtDtl);
	}	
}