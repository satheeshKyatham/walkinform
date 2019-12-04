package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ZZExistDao;
import com.godrej.properties.model.ZzholdTest;

@SuppressWarnings("unchecked")
@Repository("zZExistDao")
public class ZZExistDaoImpl extends AbstractDao<Integer, ZzholdTest> implements ZZExistDao {
	@Autowired
	private SessionFactory sessionFactory;
public ZzholdTest unitExist(String unitNo) {	 
		
	
	
	
		Criteria criteria = createEntityCriteria();		
		
		criteria.add(Restrictions.eq("sfid", unitNo));
		criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("propstrength__property_on_hold_for_reallocation__c::varchar", "t")) ));
		//criteria.add(Restrictions.not(Restrictions.eq("admin_status", "R")));
		
		/*
		 * criteria.add(Restrictions.eq("sfid", unitNo));
		 * criteria.add(Restrictions.and(Restrictions.or(Restrictions.eq("admin_status",
		 * "Y"),Restrictions.eq("senttocrm", "Y"),Restrictions.eq("sendforapproval",
		 * "Y")) )); criteria.add(Restrictions.not(Restrictions.eq("admin_status",
		 * "R")));
		 */
		
		//criteria.add(Restrictions.or(Restrictions.eq("admin_status", "Y"),Restrictions.eq("senttocrm", "Y"),Restrictions.eq("sendforapproval", "Y")));
		 
			
		//criteria.add(Restrictions.eq("isactive", "Y"));
		
		//List<ZzholdTest> req= (List<ZzholdTest>) criteria.list();
		
		
		Session session = this.sessionFactory.getCurrentSession();
		List<ZzholdTest> list  =session.createQuery("  FROM  ZzholdTest  where sfid='"+unitNo+"' and  propstrength__property_on_hold_for_reallocation__c='t' ").list();
		if(list.size()>0)
		{
			return list.get(0);
		}
		/*
		 * if(req.size()>0) { return req.get(0); }
		 */
		return null;		
	}
	

}
