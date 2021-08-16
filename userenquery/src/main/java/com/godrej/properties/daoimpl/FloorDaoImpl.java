package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.FloorDao;
import com.godrej.properties.model.Floor;

@SuppressWarnings("unchecked")
@Repository
public class FloorDaoImpl extends AbstractDao<Integer, Floor>implements FloorDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Floor> getData(String towersfid) {	 
		String whereCondition = "";
		if (towersfid != null) {
			whereCondition = "a.propstrength__tower__c in ("+towersfid+") AND ";
		} else {
			whereCondition = "";
			return null;
		}

		Session session = this.sessionFactory.getCurrentSession();
		
		List<Floor> authors=null;
		
		Query q = session.createNativeQuery(" SELECT a.id, b.propstrength__tower_name__c, a.sfid, a.propstrength__tower__c, a.propstrength__floor_name__c "
				+ " FROM salesforce.propstrength__floor__c a "
				+ " INNER JOIN salesforce.propstrength__tower__c b ON a.propstrength__tower__c = b.sfid  "
				+ " where "+whereCondition+" a.propstrength__active__c = 't'  "
				+ " order by b.propstrength__tower_name__c, a.propstrength__floor_number__c ", Floor.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}

}
