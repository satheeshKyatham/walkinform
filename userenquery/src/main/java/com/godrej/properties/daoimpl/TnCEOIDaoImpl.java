package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TnCEOIDao;
import com.godrej.properties.model.TnC;
import com.godrej.properties.model.TnCEOI;

@Repository("tnCEOIDao")
public class TnCEOIDaoImpl extends AbstractDao<Integer, TnCEOI> implements TnCEOIDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertTNCForEOI(TnCEOI tnCEOI) {
		persist(tnCEOI);
	}
	
	@Override
	public  List<TnCEOI> getTncData(String projectid) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<TnCEOI> list  =session.createQuery(" FROM  TnCEOI where project_id = '"+projectid+"'  and isactive = 'A'   ").list();
		
		if(list.size()>0)
		{
			return list;
		}
		
		return null;		
	}
	
	
}