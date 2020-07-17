package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.HoldInventoryEntryDao;
import com.godrej.properties.model.HoldInventoryEntry;

@SuppressWarnings("unchecked")
@Repository("holdInventoryEntryDao")
public class HoldInventoryEntryDaoImpl extends AbstractDao<Integer, HoldInventoryEntry> implements HoldInventoryEntryDao {
	
	private Logger logger = Logger.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void insertHoldRqst(HoldInventoryEntry action) {
		//logger.info("Before Insert************************* HOLD Issue");
		//try {
		
		persist(action);
		
		//}catch (Exception e) {
			//logger.info("insertHoldRqst Error:-",e);
			//e.printStackTrace();
		//}
		logger.info("After Insert************************* HOLD Issue");
	}
	
	
	
	public HoldInventoryEntry holdExist(String projectNameId, String customerId) {
		logger.info(" holdExist method - Project Name:"+projectNameId+" Mobile No:- "+customerId);
		Session session = this.sessionFactory.getCurrentSession();
		try /*(Session session = getNewSession())*/ {
			if (session == null) {
				return null;
			}
			List<HoldInventoryEntry> list = session.createQuery(" FROM  HoldInventoryEntry  where customer_id='"
					+ customerId + "' and project_id = '" + projectNameId + "' and statusai = 'A' ").list();

			if (list.size() > 0) {
				logger.info(" Result Size :-"+list.size());
				return list.get(0);
			}
		} catch (Exception e) {
			logger.info("HoldInventoryEntryDaoImpl Error :-",e);
		}
		return null;
	}
	
    @Override
	public HoldInventoryEntry getHolding(int userId) {
		Session session = this.sessionFactory.getCurrentSession();
		try /*(Session session = getNewSession())*/ {
			if (session == null) {
				return null;
			}
			List<HoldInventoryEntry> list = session.createQuery(" FROM  HoldInventoryEntry  where user_id='"
					+ userId + "' and holdstatusyn = 'Y' ").list();

			if (list.size() > 0) {
				logger.info(" Result Size :-"+list.size());
				return list.get(0);
			}
		} catch (Exception e) {
			logger.info("HoldInventoryEntryDaoImpl Error :-",e);
		}
		return null;
	}

	
	@Override
	public void updateForelease(HoldInventoryEntry action) {
		//logger.info(" updateForelease Method :-");
		Session session = this.sessionFactory.getCurrentSession();
		//try {
		
		Query query = session.createQuery("UPDATE HoldInventoryEntry SET holdstatusyn = '"+action.getHoldstatusyn()+"',  statusai = '"+action.getStatusai()+"',version='"+action.getVersion()+"'   WHERE sfid = '"+action.getUnitSfid()+"' and version=0 and holdstatusyn='Y' ");
		query.executeUpdate();
		
		//}
		//catch (Exception e) {
		//	logger.info(" updateForelease Error :-",e);
		//}
	}
	
	
	
	@Override
	public void updatePreviousHold(HoldInventoryEntry action) {
		
		Session session = this.sessionFactory.getCurrentSession();
		try  {
			
			Query query = session.createNativeQuery("UPDATE salesforce.gpl_cs_hold_unit_uat A SET holdstatusyn = '"+action.getHoldstatusyn()+"', statusai = '"+action.getStatusai()+"', version =(SELECT coalesce(MAX(VERSION),0) +1 FROM salesforce.gpl_cs_hold_unit_uat B WHERE A.sfid=B.sfid) WHERE A.created_at + (coalesce(hold_for_time/60000, 5) || ' minute') \\:\\: interval < now() + (interval '330 minute') AND A.holdstatusyn = 'Y' AND A.statusai = 'A' ");
			
			/*
			 * Query query = session.
			 * createNativeQuery("UPDATE salesforce.gpl_cs_hold_unit_uat A SET holdstatusyn = '"
			 * +action.getHoldstatusyn()+"', statusai = '"+action.getStatusai()
			 * +"', version =(SELECT coalesce(MAX(VERSION),0) +1 " +
			 * " FROM salesforce.gpl_cs_hold_unit_uat B WHERE A.sfid=B.sfid) " +
			 * " WHERE A.created_at + (interval '5 minute') < now() + (interval '330 minute') AND A.holdstatusyn = 'Y' AND A.statusai = 'A' "
			 * );
			 *///UPDATE salesforce.gpl_cs_hold_unit_uat SET holdstatusyn = '"+action.getHoldstatusyn()+"',  statusai = '"+action.getStatusai()+"'   WHERE  created_at + (interval '5 minute') < now() + (interval '330 minute')");
			query.executeUpdate();
			//trx.commit();
		}
		catch (Exception e) {
			logger.info("Exception ",e);
		}
	}
	
	
	
	
	
	
	
	public List<HoldInventoryEntry> holdDataExist(String projectNameId, String customerId) {
		Session session = this.sessionFactory.getCurrentSession();
		try /*(Session session = getNewSession())*/ {
			if (session == null) {
				return null;
			}
			List<HoldInventoryEntry> list = session.createQuery(" FROM  HoldInventoryEntry  where customer_id='"
					+ customerId + "' and project_id = '" + projectNameId + "' and statusai = 'A' ").list();

			if (list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			logger.info("Exception ",e);
		}

		return null;
	}



	@Override
	public int getCurrentVersion(String projectId, String unitId, String customerId) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"SELECT max(version) FROM HoldInventoryEntry WHERE unitSfid=:unitSfid ")
				.append(" AND version <> 0 AND statusai='I' AND holdstatusyn='N'");

		Session session = sessionFactory.getCurrentSession();
		Integer version = null;
		try {
			org.hibernate.query.Query<Integer> query = session.createQuery(hql.toString(), Integer.class);
			query.setParameter("unitSfid", unitId);

			version = query.getSingleResult();
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		return version == null ? 0 : version;
	}

}