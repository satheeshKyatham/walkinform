package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SchemeSourceDao;
import com.godrej.properties.model.SchemeSource;

@Repository("schemeSourceDao")
public class SchemeSourceDaoImpl extends AbstractDao<Integer, SchemeSource> implements SchemeSourceDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public void insertSchemeSource(SchemeSource schemeSource) {
		persist(schemeSource);
	}
	
	@Override
	public List<SchemeSource> getSchemeSource(String projectId, String cp_flag_yn) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		
		List<SchemeSource> list;
		
		if("".equals(cp_flag_yn)) {
			list  =session.createQuery("  from SchemeSource where project_id='"+projectId+"' and isactive='A' ").list();
		} else {
			list  =session.createQuery("  from SchemeSource where project_id='"+projectId+"' and isactive='A' and cp_flag_yn = '"+cp_flag_yn+"' ").list();
		}
		
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}
}