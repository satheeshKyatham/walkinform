package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CostSheetDao;

import com.godrej.properties.model.CostSheet;

@Repository("costSheetDao")
public class CostSheetDaoImpl extends AbstractDao<Integer, CostSheet> implements CostSheetDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	

	@Override
	public void setCostSheet(List<CostSheet> costSheets, String equery_id) {
		//update
		//Criteria criteria = createEntityCriteria();
		//criteria.add(Restrictions.eq("userId",costSheets.getUserId()));
		
		//long test = 1551845820713l;
	//	criteria.add(Restrictions.eq("timeid",  test));
		 
		
		
		/*@SuppressWarnings("rawtypes")
		Query query =   getSession().createQuery("update CostSheet set isactive= :isactive " 
					+  " where timeid = :timeid");
		query.setParameter("isactive", "N");
		query.setParameter("timeid",timeid );
		
		query.executeUpdate();*/
		
		
		
		/*List<CostSheet> req= (List<CostSheet>) criteria.list();
		if(req.size()>0) {
			CostSheet r= req.get(0);
			
			r.setIsactive("Y");
			
			sessionFactory.getCurrentSession().save(r);
		}*/
		
		
		
		//Insert
		batchPersist(costSheets);
	}	
	
	
	
	
}