package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIPreferenceDtlDao;
import com.godrej.properties.model.EOIPreferenceDtl;

@Repository("eOIPreferenceDtlDao")
public class EOIPreferenceDtlDaoImpl extends AbstractDao<Integer, EOIPreferenceDtl> implements EOIPreferenceDtlDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertEOI(List<EOIPreferenceDtl> eoiDtl) {
		batchPersist(eoiDtl);
	}	
	
	
	
	@Override
	public List<EOIPreferenceDtl> getEOIPreferenceRecord(String enqSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIPreferenceDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cs_eoi_header_dtl "
				+ " where enq_sfid = '"+enqSfid+"' order by gpl_cs_eoi_header_dtl ", EOIPreferenceDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}