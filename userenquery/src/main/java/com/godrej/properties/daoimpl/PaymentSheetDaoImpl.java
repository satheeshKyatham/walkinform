package com.godrej.properties.daoimpl;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentSheetDao;
import com.godrej.properties.model.PaymentSheet;

@Repository("paymentSheetDao")
public class PaymentSheetDaoImpl extends AbstractDao<Integer, PaymentSheet> implements PaymentSheetDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PaymentSheet> getpaymentsheet(String project_c) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<PaymentSheet> list  =session.createQuery("  from  PaymentSheet  where propstrength__property__c='"+project_c+"'").list();
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}

}