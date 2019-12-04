package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TowerBandDao;
import com.godrej.properties.model.TowerBand; 
@SuppressWarnings("unchecked")
@Repository("toweBandDao")
public class TowerBandDaoImpl extends AbstractDao<Integer, TowerBand> implements TowerBandDao {


	@Autowired
	private SessionFactory sessionFactory;
	 
	@Override
	public List<TowerBand> getTowerBand(String projectCode, String towerCode) {
		Session session = this.sessionFactory.getCurrentSession();
		List<TowerBand>  list  =(List<TowerBand>)session.createQuery(" FROM TowerBand  where projectid='"+projectCode+"'  and  towercode='"+towerCode+"'" ).list();
		if(!list.isEmpty())
		{
			return list;	
		}
		return new ArrayList<>();
	}
		 
	
	 
	 
	
}
