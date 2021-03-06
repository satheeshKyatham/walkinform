package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.PaymentEOIReportDao;
import com.godrej.properties.model.PaymentEOIReport;

@Repository("paymentEOIReportDao")
public class PaymentEOIReportDaoImpl implements PaymentEOIReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PaymentEOIReport> getPaymentEOIReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<PaymentEOIReport> authors=null;
		
		/*Query q = session.createNativeQuery(" SELECT c.region__c,"
				+ " c.name as project_name, "
				+ " b.name as enq_name, "
				+ " d.name as customer_name, "
				+ " d.mobile_number__c as customer_mobile, "
				+ " d.email as customer_email,"
				+ " d.createddate as walkin_date, "
				+ " b.walk_in_source__c,"
				+ " b.verticle__c,  "
				+ " b.closing_manager_name__c, "
				
				+ " (SELECT"
				+ " SUM ( cast(transaction_amount as double precision) ) AS total"
				+ " FROM"
				+ " salesforce.gpl_cs_eoi_payment_details where "
				+ " enq_sfid = b.sfid"
				+ " GROUP BY"
				+ " enq_sfid) as total_payment_done,"
				
				+ " (SELECT"
				+ " SUM ( cast(transaction_amount as double precision) ) AS total"
				+ " FROM"
				+ " salesforce.gpl_cs_eoi_payment_details where "
				+ " enq_sfid = b.sfid  and (isactive = 'Y' OR isactive = 'O' )"
				+ " GROUP BY"
				+ " enq_sfid) as total_payment_approved,"
				
				+ " pre.* FROM ("
				+ " SELECT  a.enq_sfid,"
				+ " a.request_id,"
				+ " a.project_sfid,"
				+ " max(CASE WHEN rn = 1 THEN typology_name END) typology_name1 ,"
				+ " max(CASE WHEN rn = 1 THEN floor_band END) floor_band1 ,"
				+ " max(CASE WHEN rn = 1 THEN tower_name END) tower_name1 , "
				+ " max(CASE WHEN rn = 2 THEN typology_name END) typology_name2, "
				+ " max(CASE WHEN rn = 2 THEN floor_band END) floor_band2,"
				+ " max(CASE WHEN rn = 2 THEN tower_name END) tower_name2"
				+ " FROM ("
				+ " select *,Row_number() over(partition by enq_sfid,request_id order by (select 1)) rn"
				+ " from salesforce.gpl_cs_eoi_header_dtl"
				+ " ) a"
				+ " GROUP BY enq_sfid,request_id, project_sfid"
				+ " ) as pre "
				+ " INNER JOIN salesforce.propstrength__request__c b ON b.sfid = pre.enq_sfid "
				+ " INNER JOIN salesforce.propstrength__projects__c c ON b.propstrength__project__c = c.sfid"
				+ " INNER JOIN salesforce.contact d ON b.propstrength__primary_contact__c = d.sfid"
				 
				+ " where "+whereCondition+"  ", EOIReport.class);*/
		
		
		Query q = session.createNativeQuery(" SELECT * FROM salesforce.vw_payment_eoi_report "
				+ " where "+whereCondition+"  ", PaymentEOIReport.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}	