package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
/*import org.apache.log4j.Logger;*/
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	/*private Logger logger = Logger.getLogger(getClass());*/
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	public BalanceDetails insertBalanceDetails(BalanceDetails action) {
		//if already offer Exists for Enquiry Check
		//Session session = this.sessionFactory.getCurrentSession();	
		//List<BalanceDetails> list =session.createQuery(" from BalanceDetails where enquiry_sfid='"+action.getEnquiry_sfid()+"' and isactive='A'").list();
		//if(list.size()>0)
		//{
			
			/* Commented by A */
			// We want all offer against same enq
			// Update Query comes when if customer BOOK 101 and cancel it and again BOOK same unit 101, than we need to UPDATE old record as INACTIVE for unit 101 
		
			/*Query query = session.createQuery(" Update BalanceDetails set isactive ='I' where enquiry_sfid='"+action.getEnquiry_sfid()+"'");
			query.executeUpdate();*/
			try
			{
				persist(action);
			}
			catch (Exception e) {
				logger.error("Error on BalanceDetails Insert:-"+e);
			}
			
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

	@Override
	public void eCRMCancelledOfferInactive() {
		Session session = this.sessionFactory.getCurrentSession();	
		try
		{
			Query query = session.createNativeQuery(" update salesforce.gpl_cs_balance_details set isactive='I' from (select csbalance.offer_sfid,csbalance.isactive as offerstate,csbalance.gpl_cs_balance_details_id as balanceid from salesforce.gpl_cs_balance_details csbalance inner join salesforce.propstrength__offer__c offer on(csbalance.offer_sfid=offer.sfid) "
					+ " where csbalance.offer_sfid is not null and csbalance.isactive='A' and offer.PropStrength__Status__c ='Cancelled') a where a.balanceid=gpl_cs_balance_details_id and isactive=a.offerstate");
			query.executeUpdate();
		}catch (Exception e) {
			logger.error("Error eCRMCancelledOfferInactive:-",e);
		}
	}
}
