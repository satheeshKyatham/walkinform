package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.TriggerLogDao;
import com.godrej.properties.model.TriggerLog;

@Repository("triggerLogDao")
public class TriggerLogDaoImpl implements TriggerLogDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TriggerLog> getTriggerLogDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<TriggerLog> authors=null;
		
		Query q = session.createNativeQuery("SELECT * "
				+ " FROM salesforce._trigger_log where "+whereCondition+"", TriggerLog.class);
				//+ " FROM salesforce._trigger_log limit 2", TriggerLog.class);
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
