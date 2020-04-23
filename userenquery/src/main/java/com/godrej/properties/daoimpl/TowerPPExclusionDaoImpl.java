package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.TowerPPExclusionDao;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.TowerPPExclusion;

@SuppressWarnings("unchecked")
@Repository("towerPPExclusionDao")
public class TowerPPExclusionDaoImpl extends AbstractDao<Integer, TowerPPExclusion> implements TowerPPExclusionDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public TowerPPExclusion insertTowerPPExclusion(TowerPPExclusion towerPP) {
		try{
			persist(towerPP);
			return towerPP;
		}catch(Exception e){
			Log.error("not saved");
			e.printStackTrace();
		}
		return towerPP;
	}


	@Override
	public List<TowerPPExclusion> getTowerPPExclusionQuery() {
		Session session = this.sessionFactory.getCurrentSession();
		List<TowerPPExclusion> data=new ArrayList<TowerPPExclusion>();		
		List<TowerPPExclusion> list = session.createQuery(" from TowerPPExclusion where isactive='A'").list();
		if (list.size() > 0){
			return list;
		}
		return data;
	}


	@Override
	public boolean deleteTowerPPExclusionRecordQuery(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("delete from TowerPPExclusion  WHERE id =:id ");
		q.setParameter ("id", id);
		int deleted = q.executeUpdate();
		if (deleted > 0) {
			return true;
		}
		return false;
		
	}


	@Override
	public boolean getTowerPPQuery(TowerPPExclusion data) {
		Session session = sessionFactory.getCurrentSession();
		List<EOIData> list = session.createQuery(" from TowerPPExclusion where payment_plan_sfid ='"+data.getPayment_plan_sfid()+"' and"
				+ " tower_sfid='"+data.getTower_sfid()+"'").list();
		if (list.size() > 0){
			return true;
		}
		return false;
	}
}
