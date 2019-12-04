package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqAndBookingDtlDao;
import com.godrej.properties.model.EnqAndBookingDtl;

@Repository("enqAndBookingDtlDao")
public class EnqAndBookingDtlDaoImpl extends AbstractDao<Integer, EnqAndBookingDtl> implements EnqAndBookingDtlDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<EnqAndBookingDtl> getEnqAndBookingData(String enqId, String applicationBookingId) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EnqAndBookingDtl> authors=null;
		
		
		Query q = session.createNativeQuery("SELECT a.propstrength__total_basic_sales_price__c, g.marketing_project_name__c, b.walk_in_source__c, b.is_purchase_for_self_use_or_investment__c, g.jv_name__c, g.jv_street__c, g.jv_state__c, g.jv_country__c, g.jv_city__c, g.propstrength__description__c, a.project_name__c, e.project_phases__c, a.propstrength__booking_date__c, a.sap_customer_code__c, a.name, f.propstrength__floor_name__c,a.propstrength__revised_agreement_amount__c, d.appurtenant_area_sq_mt__c, d.carpet_area_converted__c,e.propstrength__tower_name__c, d.propstrength__property_name__c, c.name as broker_name, c.mobile_no__c as broker_mobile, b.propstrength__enquiry_type__c, a.id, a.application_booking_id_18__c, a.enquiry18digit__c  "
				+ " FROM salesforce.propstrength__application_booking__c a "
				+ " INNER JOIN salesforce.propstrength__request__c b ON a.enquiry18digit__c = b.sfid "
				+ " LEFT JOIN salesforce.account c ON a.propstrength__broker_account__c = c.sfid  and c.recordtypeid = '0126F000000uazCQAQ' "
				+ " INNER JOIN salesforce.propstrength__property__c d ON a.propstrength__property__c = d.sfid "
				+ " INNER JOIN salesforce.propstrength__tower__c e ON d.propstrength__tower__c = e.sfid "
				+ " INNER JOIN salesforce.propstrength__floor__c f ON d.propstrength__floor__c = f.sfid "
				+ " INNER JOIN salesforce.propstrength__projects__c g ON a.propstrength__project__c = g.sfid "
				
				+ " where a.enquiry18digit__c = '"+enqId+"' and a.application_booking_id_18__c = '"+applicationBookingId+"'  ", EnqAndBookingDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		
		
		
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
