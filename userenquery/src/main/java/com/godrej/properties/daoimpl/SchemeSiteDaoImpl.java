package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SchemeSiteDao;
import com.godrej.properties.model.SchemeSite;

@Repository("schemeSiteDao")
public class SchemeSiteDaoImpl extends AbstractDao<Integer, SchemeSite> implements SchemeSiteDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertSchemeSite(SchemeSite schemeSite) {
		persist(schemeSite);
	}
	
	@Override
	public List<SchemeSite> getSchemeSite(String projectId, String cp_flag_yn) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		
		List<SchemeSite> list;
		
		if("".equals(cp_flag_yn)) {
			list  =session.createQuery("  from SchemeSite where project_id='"+projectId+"' and isactive='A' ").list();
		} else {
			list  =session.createQuery("  from SchemeSite where project_id='"+projectId+"' and isactive='A' and cp_flag_yn = '"+cp_flag_yn+"' ").list();
		}
		
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}
	
}