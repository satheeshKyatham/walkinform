package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.HeaderSchemeDao;
import com.godrej.properties.model.HeaderScheme;

@Repository("headerSchemeDao")
public class HeaderSchemeDaoImpl extends AbstractDao<Integer, HeaderScheme> implements HeaderSchemeDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<HeaderScheme> getHeaderSchemeChargs(String projectId) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<HeaderScheme> authors=null;
		
		
		Query q = session.createNativeQuery("SELECT a.id, "
				+ " a.scheme, "
				+ " a.rate, "
				+ " a.isactive, "
				+ " a.project_id, "
				+ " a.percentage, "
				+ " a.absolute_amount, "
				+ " a.zero_govt_charges,  "
				+ " a.source_name,  "
				+ " a.site_name,  "
				+ " a.promotional_name  "
				
				+ " FROM salesforce.gpl_cs_scheme a "
				+ " where a.isactive = 'A' and   a.project_id = '"+projectId+"' " , HeaderScheme.class); 
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}