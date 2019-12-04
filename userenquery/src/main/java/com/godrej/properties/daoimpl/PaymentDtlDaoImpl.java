package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentDtlDao;
import com.godrej.properties.model.PaymentDtl;
import com.godrej.properties.model.PaymentPlanList;

@Repository("paymentDtlDao")
public class PaymentDtlDaoImpl extends AbstractDao<Integer, PaymentDtl> implements PaymentDtlDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertPaymentDtl(List<PaymentDtl> pymtDtl) {
		batchPersist(pymtDtl);
	}

	@Override
	public List<PaymentDtl> getPrePaymentData(String enq) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<PaymentDtl> list =session.createQuery(" from PaymentDtl where enq_sfid='"+enq+"' ").list();
		
		if(list.size()>0)
			return list;
		return null;
	}	
}