package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CarparkTypeEOIDao;
import com.godrej.properties.model.CarparkTypeEOI;
import com.godrej.properties.model.TowerBand; 
@SuppressWarnings("unchecked")
@Repository("carparkTypeEOIDao")
public class CarparkTypeEOIDaoImpl extends AbstractDao<Integer, CarparkTypeEOI> implements CarparkTypeEOIDao {

	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public List<CarparkTypeEOI> getTowerBand(String projectCode) {
		Session session = this.sessionFactory.getCurrentSession();
		List<CarparkTypeEOI>  list  =(List<CarparkTypeEOI>)session.createQuery(" FROM CarparkTypeEOI  where project_sfid='"+projectCode+"'  AND isactive = 'A' " ).list();
		if(!list.isEmpty())
		{
			return list;	
		}
		return new ArrayList<>();
	}
}