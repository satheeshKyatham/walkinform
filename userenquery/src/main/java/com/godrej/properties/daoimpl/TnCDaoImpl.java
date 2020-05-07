package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TnCDao;
import com.godrej.properties.model.TnC;

@Repository("tnCDao")
public class TnCDaoImpl extends AbstractDao<Integer, TnC> implements TnCDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertTNCForPP(TnC tnC) {
		persist(tnC);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public  List<TnC> getTncData(String ppId, String projectid, String tower_sfid) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			List<TnC> list  =session.createQuery(" FROM  TnC where project_id = '"+projectid+"' "
					+ " AND pymt_plan_id = '"+ppId+"' AND tower_sfid is NULL and isactive = 'A'  order by id DESC ").list();
			if(list.size()>0) {
				return list;
			} else {
				List<TnC> listTowerAndPP  =session.createQuery(" FROM  TnC where project_id = '"+projectid+"' "
						+ " AND pymt_plan_id = '"+ppId+"' AND tower_sfid='"+tower_sfid+"' AND isactive = 'A'  order by id DESC ").list();
				if(listTowerAndPP.size()>0) {	
					return listTowerAndPP;
				} else {
					List<TnC> listTower  =session.createQuery(" FROM  TnC where project_id = '"+projectid+"' "
							+ " AND pymt_plan_id = '' AND tower_sfid='"+tower_sfid+"' AND isactive = 'A'  order by id DESC ").list();
					
					if(listTower.size()>0) {	
						return listTower;
					} else {
						List<TnC> list1  =session.createQuery(" FROM  TnC where project_id = '"+projectid+"' "
								+ " AND pymt_plan_id = '' AND tower_sfid is NULL AND isactive = 'A'  order by id DESC ").list();
						if(list1.size()>0) {	
							return list1;
						} else {
							return null;
						}
						
					}
				}
			} 
		} catch (Exception e) {
			Log.info("Get TnC for Cost Sheet Error:- ",e);
			return null;
		}
		
	}
	
	
}