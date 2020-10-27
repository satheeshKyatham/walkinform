package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqAndOfferDtlDao;
import com.godrej.properties.model.EnqAndOfferDtl;

@Repository("enqAndOfferDtlDao")
public class EnqAndOfferDtlDaoImpl extends AbstractDao<Integer, EnqAndOfferDtl> implements EnqAndOfferDtlDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<EnqAndOfferDtl> getEnqAndOfferData(String enqId, String offerSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EnqAndOfferDtl> authors=null;
		
		
		Query q = session.createNativeQuery("SELECT "
				/*+ " a.propstrength__total_basic_sales_price__c, "
				+ " a.application_booking_id_18__c, "
				+ " a.enquiry18digit__c,  "
				+ " a.project_name__c, "
				+ " a.sap_customer_code__c, "
				+ " a.name, "
				+ " a.propstrength__revised_agreement_amount__c, "*/
				
				+ " a.id, "
				+ " a.offer_date__c, "
				+ " a.name, "
				+ " a.sfid, "
				+ " a.propstrength__request__c, "
				+ " a.propstrength__total_basic_sale_price__c, "
				+ " a.propstrength__net_revenue__c, "
				+ " a.propstrength__total_other_charges__c, "
				+ " g.marketing_project_name__c, "
				+ " f.propstrength__floor_name__c, "
				+ " b.walk_in_source__c, "
				+ " b.is_purchase_for_self_use_or_investment__c, "
				+ " g.jv_name__c, "
				+ " g.jv_street__c, "
				+ " g.jv_state__c, "
				+ " g.jv_country__c, "
				+ " g.jv_city__c, "
				+ " g.propstrength__description__c, "
				+ " e.project_phases__c, "
				+ " d.appurtenant_area_sq_mt__c, "
				+ " CASE  WHEN d.appurtenant_area_sq_ft__c IS NULL THEN CAST(0 as double precision)  ELSE d.appurtenant_area_sq_ft__c  END AS appurtenant_area_sq_ft__c, "
				+ " d.carpet_area_converted__c,"
				+ " e.propstrength__tower_name__c, "
				+ " d.propstrength__property_name__c, "
				+ " c.name as broker_name, "
				+ " c.mobile_no__c as broker_mobile, "
				+ " b.propstrength__enquiry_type__c, "
				+ " b.propstrength__primary_contact__c, "
				+ " g.rera_registration_number__c, "
				+ " d.sfid as prop_sfid, "
				+ " d.open_balc_sq_mt__c, "
				+ " CASE  WHEN d.open_balc_sq_ft__c IS NULL THEN CAST(0 as double precision)  ELSE d.open_balc_sq_ft__c  END AS open_balc_sq_ft__c, "
				+ " d.saleable_area__c, "
				+ " d.wing_block__c, "
				+ " e.rera_registration_number__c as tower_rera_no, "
				
				+ " d.length_sqm__c, "
				+ " d.breadth_sqm__c, "
				+ " d.plot_area_sqyd__c, "
				+ " e.sfid as tower_sfid "
				
				+ " FROM salesforce.propstrength__offer__c a "
				+ " INNER JOIN salesforce.propstrength__request__c b ON a.propstrength__request__c = b.sfid  "
				+ " LEFT JOIN salesforce.account c ON b.propStrength__broker_account__c = c.sfid  and c.recordtypeid = '0126F000000uazCQAQ' "
				+ " INNER JOIN salesforce.propstrength__property__c d ON a.propstrength__property__c = d.sfid  "
				+ " INNER JOIN salesforce.propstrength__tower__c e ON d.propstrength__tower__c = e.sfid  "
				+ " INNER JOIN salesforce.propstrength__floor__c f ON d.propstrength__floor__c = f.sfid  "
				+ " INNER JOIN salesforce.propstrength__projects__c g ON a.propstrength__project__c = g.sfid  "
				
				+ " where a.propstrength__request__c = '"+enqId+"' and a.sfid = '"+offerSfid+"'  ", EnqAndOfferDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		//System.out.println("Final Size::"+authors.size());
		
		
		
		
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}
