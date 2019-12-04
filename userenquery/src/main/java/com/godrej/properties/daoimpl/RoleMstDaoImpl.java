package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.RoleMstDao;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.model.RoleMst;

//@SuppressWarnings("unchecked")
@Repository("roleMstDao")
public class RoleMstDaoImpl extends AbstractDao<Integer, RoleMst> implements RoleMstDao {

	
	
	
	
	
	//RoleMst roleDtl(String userId);
	
	
	
	//New added
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<RoleMst> roleDtl(String userId){
		Session session = this.sessionFactory.getCurrentSession();	
		List<RoleMst> list =new ArrayList<>();
		list=session.createQuery("  from  RoleMst where user_id ='"+userId+"'").list(); //where propstrength__payment_plan__c='"+project_code+"'

		if(list.size()>0)
			 return list;
		 
		 return null;
		
	}
	
	
	
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public RoleMst roleDtl(String userId) {
	 * 
	 * Criteria criteria = createEntityCriteria();
	 * criteria.add(Restrictions.eq("user_id", userId));
	 * 
	 * //criteria.addOrder(Order.desc("id")); //return (List<EmpDtl>)
	 * criteria.list();
	 * 
	 * List<RoleMst> req= (List<RoleMst>) criteria.list(); if(req.size()>0) { return
	 * req.get(0); } return null;
	 * 
	 * }
	 */

}