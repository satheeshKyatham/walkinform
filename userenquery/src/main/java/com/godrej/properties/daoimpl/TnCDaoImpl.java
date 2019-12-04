package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TnCDao;
import com.godrej.properties.model.TnC;

@Repository("tnCDao")
public class TnCDaoImpl extends AbstractDao<Integer, TnC> implements TnCDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertTNCForPP(TnC tnC) {
		persist(tnC);
	}
	
	@Override
	public  List<TnC> getTncData(String ppId, String projectid) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TnC> list  =session.createQuery(" FROM  TnC where project_id = '"+projectid+"'  and isactive = 'A'   ").list();
		
		if(list.size()>0)
		{
			return list;
		}
		
		return null;		
	}
	
	
}