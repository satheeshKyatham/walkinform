package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.EOIReportDao;
import com.godrej.properties.model.AllotmentMISReport;
import com.godrej.properties.model.AllotmentReport;
import com.godrej.properties.model.EOIReport;

@Repository("eOIReportDao")
public class EOIReportDaoImpl implements EOIReportDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<EOIReport> getEOIReportDtl(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIReport> authors=null;
		
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
		
		
		Query q = session.createNativeQuery(" SELECT * FROM salesforce.vw_eoi_report "
				+ " where "+whereCondition+"  ", EOIReport.class);
		
		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}

	@Override
	public List<AllotmentReport> getAllotmentReport(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<AllotmentReport> allot=null;
		Query q = session.createNativeQuery(" SELECT * FROM salesforce.vw_allotment_report "
				+ " where "+whereCondition+"  ", AllotmentReport.class);
		
		allot = q.getResultList();
		
		if (allot.size() > 0)
			return allot;

		return null;
	}

	@Override
	public AllotmentMISReport getAllotmentMISReport(String whereCondition) {
		AllotmentMISReport alotMIS = new AllotmentMISReport();
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createNativeQuery("select sum(PropStrength__Super_Area__c) as TotalSelableAreaSold,(sum(propstrength__total_basic_sale_price__c)/10000000) as TotalBSP,count(id) as unitcount from salesforce.propstrength__offer__c where propstrength__project__c='"+whereCondition+"' and propstrength__status__c='Closed Won'");
		Object[] result = (Object[])q.getSingleResult();
		
		Query bookingcount = session.createNativeQuery("select count(id) bookingcount from salesforce.propstrength__application_booking__c where propstrength__project__c='"+whereCondition+"' and booking_status__c='Deal Approved'");
		//Object[] resultBooking = (Object[])bookingcount.getSingleResult();
		
		Query totalEToken = session.createNativeQuery("select count(nv_token_id) as totalEtoken from salesforce.nv_token where isactive='Y' and projectname='"+whereCondition+"' and type='E'");
		//Object[] resultEToken = (Object[])totalEToken.getSingleResult();
		
		Query approvedCount = session.createNativeQuery("select count(id) as ApprovedCount from salesforce.nv_eoi_form where kycapproval_status='Y' and project_sfid='"+whereCondition+"'");
		//Object[] resultApproved = (Object[])approvedCount.getSingleResult();
		
		Query coverd1bhk = session.createNativeQuery("select count(car_park_type) from salesforce.gpl_cs_balance_details where project_sfid='"+whereCondition+"' and isactive='A' and car_park_type='Covered (1 BHK)' group by car_park_type ");
		
		Query coverd2bhk = session.createNativeQuery("select count(car_park_type) from salesforce.gpl_cs_balance_details where project_sfid='"+whereCondition+"' and isactive='A' and car_park_type='Covered (2 BHK)' group by car_park_type ");
		
		Query coverd3bhk = session.createNativeQuery("select count(car_park_type) from salesforce.gpl_cs_balance_details where project_sfid='"+whereCondition+"' and isactive='A' and car_park_type='Covered (3 BHK)' group by car_park_type ");

		
		Query stack1bhk = session.createNativeQuery("select count(car_park_type) from salesforce.gpl_cs_balance_details where project_sfid='"+whereCondition+"' and isactive='A' and car_park_type='Stack (1 BHK)' group by car_park_type ");
		Query stack2bhk = session.createNativeQuery("select count(car_park_type) from salesforce.gpl_cs_balance_details where project_sfid='"+whereCondition+"' and isactive='A' and car_park_type='Stack (2 BHK)' group by car_park_type ");
		
		
		
		Query eOIHold = session.createNativeQuery("select count(hold_reason) as EOIHold from salesforce.gpl_cs_hold_admin_unit where project_id='"+whereCondition+"' and  eoi_unit_locked=true and hold_reason='temp' and hold_status=true and version=0");
		Query blockInv = session.createNativeQuery("select count(hold_reason) as block from salesforce.gpl_cs_hold_admin_unit where project_id='"+whereCondition+"' and hold_reason='block' and hold_status=true and version=0");
		

				

		//System.out.println("***********"+result[1].toString());
		
		alotMIS.setTotalArealSold(result[0] == null ? "0" : result[0].toString());
		alotMIS.setTotalBSP(result[1] == null ? "0" : result[1].toString());
		alotMIS.setUnitcount(result[2] == null ? "" : result[2].toString());
		
		
		alotMIS.setKycApprovedCount(approvedCount.getSingleResult().toString());
		alotMIS.setBookingcount(bookingcount.getSingleResult().toString());
		alotMIS.setTotalEtoken(totalEToken.getSingleResult().toString());
		if(coverd1bhk.getResultList().size()>0)
			alotMIS.setCoverd1bhk(coverd1bhk.getResultList().get(0).toString());
		else
			alotMIS.setCoverd1bhk("");
		if(coverd2bhk.getResultList().size()>0)
			alotMIS.setCoverd2bhk(coverd2bhk.getResultList().get(0).toString());
		else
			alotMIS.setCoverd2bhk("");
		if(coverd3bhk.getResultList().size()>0)
			alotMIS.setCoverd3bhk(coverd3bhk.getResultList().get(0).toString());
		else
			alotMIS.setCoverd3bhk("");
		
		if(stack1bhk.getResultList().size()>0)
			alotMIS.setStack1bhk(stack1bhk.getResultList().get(0).toString());
		else
			alotMIS.setStack1bhk("");
		
		if(stack2bhk.getResultList().size()>0)
			alotMIS.setStack2bhk(stack2bhk.getResultList().get(0).toString());
		else
			alotMIS.setStack2bhk("");
		
		alotMIS.setHoldInventoryCount(eOIHold.getSingleResult().toString());
		alotMIS.setBlockedInventoryCount(blockInv.getSingleResult().toString());
		return alotMIS;
	}
}	