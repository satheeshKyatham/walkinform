package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.TriggerLogArchiveDao;
import com.godrej.properties.model.TriggerLogArchive;

@Repository("triggerLogArchiveDao")
public class TriggerLogArchiveDaoImpl implements TriggerLogArchiveDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TriggerLogArchive> getTriggerLogArchiveDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<TriggerLogArchive> authors=null;
		
		Query q = session.createNativeQuery("SELECT * "
				//+ " FROM salesforce._trigger_log_archive  where "+whereCondition+"", TriggerLogArchive.class);
				+ " FROM salesforce._trigger_log_archive  where "+whereCondition+"", TriggerLogArchive.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}