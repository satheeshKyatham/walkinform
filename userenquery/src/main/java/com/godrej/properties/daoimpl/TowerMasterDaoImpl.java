package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PaymentPlanDao;
import com.godrej.properties.dao.TowerMasterDao;
import com.godrej.properties.model.PaymentPlan;
import com.godrej.properties.model.TowerMaster; 
@SuppressWarnings("unchecked")
@Repository("towerMasterDao")
@Transactional
public class TowerMasterDaoImpl extends AbstractDao<Integer, TowerMaster> implements TowerMasterDao {


	@Autowired
	private SessionFactory sessionFactory;
	public TowerMasterDaoImpl() {
		
	}
	 
	@Override
	public List<TowerMaster> getTowerMaster(String project_code) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TowerMaster> list  =session.createQuery("  FROM  TowerMaster  where   propstrength__project_name__c= '"+project_code+"' and d4u_active__c='t' order by  tower_name__c ").list();
		if(list.size()>0)
		{
			return list;
		}
		return list;
	}

	@Override
	public TowerMaster getTowerMasterDetails(String towersfid) {
		Session session = this.sessionFactory.getCurrentSession();
		
		List<TowerMaster> list  =session.createQuery("  FROM TowerMaster where sfid= '"+towersfid+"' ").list();
		if(list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

 	 
	
}
