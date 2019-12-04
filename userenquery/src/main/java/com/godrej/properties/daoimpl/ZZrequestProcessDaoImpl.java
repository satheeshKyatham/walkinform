package com.godrej.properties.daoimpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ZZrequestProcessDao;
import com.godrej.properties.model.ZzholdTest;



@SuppressWarnings("unchecked")
@Repository("zZrequestProcessDao")

public class ZZrequestProcessDaoImpl extends AbstractDao<Integer, ZzholdTest> implements ZZrequestProcessDao {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void zzupdateRqst(ZzholdTest action) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("UPDATE ZzholdTest SET propstrength__property_on_hold_for_reallocation__c = '"+action.getPropstrength__property_on_hold_for_reallocation__c()+"',  registration_crm_user__c = '"+action.getRegistration_crm_user__c()+"'   WHERE sfid = '"+action.getSfid()+"' ");
		query.executeUpdate();
	}
	
}
