package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.SchemePromotionalDao;
import com.godrej.properties.model.SchemePromotional;

@Repository("schemePromotionalDao")
public class SchemePromotionalDaoImpl extends AbstractDao<Integer, SchemePromotional> implements SchemePromotionalDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertSchemePromotional(SchemePromotional schemePromotional) {
		persist(schemePromotional);
	}
	
	@Override
	public List<SchemePromotional> getSchemePromotional(String projectId, String cp_flag_yn) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		
		List<SchemePromotional> list;
		
		if("".equals(cp_flag_yn)) {
			list  =session.createQuery("  from SchemePromotional where project_id='"+projectId+"' and isactive='A' ").list();
		} else {
			list  =session.createQuery("  from SchemePromotional where project_id='"+projectId+"' and isactive='A' and cp_flag_yn = '"+cp_flag_yn+"' ").list();
		}
		
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}
	
}