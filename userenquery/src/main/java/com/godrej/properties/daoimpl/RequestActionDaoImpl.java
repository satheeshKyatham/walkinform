package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.RequestActionDao;
import com.godrej.properties.model.RequestAction;

@SuppressWarnings("unchecked")
@Repository("requestActionDao")

public class RequestActionDaoImpl extends AbstractDao<Integer, RequestAction> implements RequestActionDao {
	
	@Override
	public List<RequestAction> rqstForAdmin (String userId) {
		Criteria criteria = createEntityCriteria();
		//criteria.add(Restrictions.isNull("isPaymentDone"));
		//criteria.add(Restrictions.isNull("admin_status"));
		criteria.add(Restrictions.eq("admin_status", "Z"));
		//criteria.add(Restrictions.eq("admin_status", "NULL"));
		criteria.add(Restrictions.eq("scheme_type", "other"));
		criteria.add(Restrictions.eq("sendforapproval", "Y"));
		
		
		criteria.addOrder(Order.desc("id"));
		
		return (List<RequestAction>) criteria.list();
	}
	
	
	@Override
	public List<RequestAction> getRqstForSales (String userId) {
		Criteria criteria = createEntityCriteria();
		//criteria.add(Restrictions.eq("scheme_type", "other"));
		
		
		//criteria.add(Restrictions.eq("admin_status", "A"));
		
		//criteria.add(Restrictions.eq("sendforapproval", "Y"));
		
		//criteria.addOrder(Order.desc("id"));
		
		
		criteria.add(Restrictions.or(Restrictions.eq("scheme_type", "other"),Restrictions.eq("admin_status", "A"), Restrictions.eq("sendforapproval", "Y"),Restrictions.eq("senttocrm", "Y") ));
		
		
		return (List<RequestAction>) criteria.list();
	}
}
