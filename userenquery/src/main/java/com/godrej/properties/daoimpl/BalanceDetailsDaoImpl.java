package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BalanceDetailsDao;
import com.godrej.properties.model.BalanceDetails;

@SuppressWarnings("unchecked")
@Repository("balanceDetailsDao")
public class BalanceDetailsDaoImpl extends AbstractDao<Integer, BalanceDetails> implements BalanceDetailsDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public BalanceDetails insertBalanceDetails(BalanceDetails action) {
		//if already offer Exists for Enquiry Check
		Session session = this.sessionFactory.getCurrentSession();	
		//List<BalanceDetails> list =session.createQuery(" from BalanceDetails where enquiry_sfid='"+action.getEnquiry_sfid()+"' and isactive='A'").list();
		//if(list.size()>0)
		//{
			
			/* Commented by A */
			// We want all offer against same enq
			// Update Query comes when if customer BOOK 101 and cancel it and again BOOK same unit 101, than we need to UPDATE old record as INACTIVE for unit 101 
		
			/*Query query = session.createQuery(" Update BalanceDetails set isactive ='I' where enquiry_sfid='"+action.getEnquiry_sfid()+"'");
			query.executeUpdate();*/
			
			persist(action);
			
			return action;
		//}
		//else
			//persist(action);
		
			//return action;
	}

	@Override
	public List<BalanceDetails> getCreatedOffersList(String projectid) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<BalanceDetails> list =session.createQuery(" from BalanceDetails where project_sfid='"+projectid+"'").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}
}
