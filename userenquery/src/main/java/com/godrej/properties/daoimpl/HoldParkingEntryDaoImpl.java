package com.godrej.properties.daoimpl;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.HoldParkingEntryDao;
import com.godrej.properties.model.HoldParkingEntry;

@SuppressWarnings("unchecked")
@Repository("holdParkingEntryDao")
public class HoldParkingEntryDaoImpl extends AbstractDao<Integer, HoldParkingEntry> implements HoldParkingEntryDao {
	
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void insertHoldRqst(HoldParkingEntry action) {
		//logger.info("Before Insert************************* HOLD Issue");
		//try {
		
		persist(action);
		
		//}catch (Exception e) {
			//logger.info("insertHoldRqst Error:-",e);
			//e.printStackTrace();
		//}
		logger.info("After Insert************************* HOLD Issue");
	}
	
	@Override
	public void updateForelease(HoldParkingEntry action) {
		Session session = this.sessionFactory.getCurrentSession();
		try  {
			
			String flatCondition = "";
			
			if (action.getFlatsfid() != null) {
				flatCondition = " AND A.flatsfid = '"+action.getFlatsfid()+"' ";
			} else {
				flatCondition = "";
			}
			
			Query query = session.createNativeQuery(" UPDATE salesforce.gpl_cs_hold_parking A "
					+ " SET holdstatusyn = '"+action.getHoldstatusyn()+"', statusai = '"+action.getStatusai()+"', "
					+ " version =(SELECT coalesce(MAX(VERSION),0) +1 FROM salesforce.gpl_cs_hold_parking B WHERE A.parkingsfid=B.parkingsfid) "
					
					+ " WHERE A.project_id = '"+action.getProject_id()+"' "
					+ flatCondition
					+ " AND A.user_id = '"+action.getUser_id()+"' AND A.holdstatusyn = 'Y' AND A.statusai = 'A' ");
			
			query.executeUpdate();
		}
		catch (Exception e) {
			logger.info("Exception ",e);
		}
	}
	
	@Override
	public int getCurrentVersion(String parkingsfid) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"SELECT max(version) FROM HoldParkingEntry WHERE parkingsfid=:parkingsfid ")
				.append(" AND version <> 0 AND statusai='I' AND holdstatusyn='N'");

		Session session = sessionFactory.getCurrentSession();
		Integer version = null;
		try {
			org.hibernate.query.Query<Integer> query = session.createQuery(hql.toString(), Integer.class);
			query.setParameter("parkingsfid", parkingsfid);

			version = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return version == null ? 0 : version;
	}
	
	@Override
	public void updateParkingPreviousHold(HoldParkingEntry action) {
		
		Session session = this.sessionFactory.getCurrentSession();
		try  {
			
			Query query = session.createNativeQuery("UPDATE salesforce.gpl_cs_hold_parking A SET holdstatusyn = '"+action.getHoldstatusyn()+"', statusai = '"+action.getStatusai()+"', version =(SELECT coalesce(MAX(VERSION),0) +1 FROM salesforce.gpl_cs_hold_parking B WHERE A.parkingsfid=B.parkingsfid) WHERE A.created_at + (coalesce(hold_for_time/1000, 5) || 'seconds') \\:\\: interval < now() + (interval '330 minute') AND A.holdstatusyn = 'Y' AND A.statusai = 'A' ");
			
			query.executeUpdate();
			//trx.commit();
		}
		catch (Exception e) {
			logger.info("Exception ",e);
		}
	}
}