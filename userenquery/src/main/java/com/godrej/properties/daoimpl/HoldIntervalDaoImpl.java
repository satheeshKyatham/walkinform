package com.godrej.properties.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.HoldIntervalDao;
import com.godrej.properties.model.Inventory;

@SuppressWarnings("unchecked")
@Repository("holdIntervalDao")
public class HoldIntervalDaoImpl extends AbstractDao<Integer, Inventory> implements HoldIntervalDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	static Logger logger = Logger.getLogger(HoldIntervalDaoImpl.class);

	public Inventory unitExist(String unitSfid, String projectNameId, String towerCode) {
		logger.info(" unitExist method - unitSfid :"+unitSfid+" Project Name:"+projectNameId+" Tower Code:- "+towerCode);
		Session session = this.sessionFactory.getCurrentSession();
		try  {
			if (session == null) {
				return null;
			}
			List<Inventory> list = session.createQuery(" FROM  Inventory  where propstrength__project_name__c='"
					+ projectNameId + "'  and  tower_code__c='" + towerCode
					+ "' and propstrength__active__c='t' and sfid='" + unitSfid + "' and holdstatusyn='Y' ").list();

			logger.info(" FROM  Inventory  where propstrength__project_name__c='" + projectNameId
					+ "'  and  tower_code__c='" + towerCode + "' and propstrength__active__c='t' and sfid='" + unitSfid
					+ "' and holdstatusyn='Y' ");

			if (list.size() > 0) {
				logger.info("unitExist return Count:-"+list.size());
				return list.get(0);
			}

		} catch (Exception e) {
			logger.info("HoldIntervalDaoImpl unitExist Query Error:- ",e);
			e.printStackTrace();
		}
		logger.info("unitExist return null");
		return null;
	}

	@Override
	public Inventory getHeldUnit(String unitSfid) {
		logger.info(" unitExist method - unitSfid :"+unitSfid);
		Session session = this.sessionFactory.getCurrentSession();
		try  {
			if (session == null) {
				return null;
			}
			List<Inventory> list = session.createQuery(" FROM  Inventory  where "
					+ " propstrength__active__c='t' and sfid='" + unitSfid + "' and holdstatusyn='Y' ").list();

			logger.info(" FROM  Inventory  where  propstrength__active__c='t' and sfid='" + unitSfid
					+ "' and holdstatusyn='Y' ");

			if (list.size() > 0) {
				logger.info("unitExist return Count:-"+list.size());
				return list.get(0);
			}

		} catch (Exception e) {
			logger.info("HoldIntervalDaoImpl unitExist Query Error:- ",e);
			e.printStackTrace();
		}
		logger.info("unitExist return null");
		return null;

	}
}