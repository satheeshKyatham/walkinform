package com.godrej.properties.daoimpl;

import java.util.ArrayList;
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
import com.godrej.properties.model.FacingDashboard;
import com.godrej.properties.model.TowerDashboard;
import com.godrej.properties.model.UnitFacingCount;
import com.godrej.properties.model.UnitTowerCount;

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
		
		// Count the records
		Query countQuery = session.createNativeQuery(" SELECT COUNT(*) FROM salesforce.propstrength__offer__c where propstrength__status__c = 'Closed Won' "
				+ " AND propstrength__project__c "+whereCondition+" ");
		
		long count = ((Number) countQuery.getSingleResult()).intValue();
  	
		String strRowCount = Long.toString(count);
	  	
	  	if (count <= 5000) {
	  		Query q = session.createNativeQuery(" SELECT * FROM salesforce.vw_allotment_report "
					+ " where project_sfid "+whereCondition+" order by offer_date__c desc  ", AllotmentReport.class);
			allot = q.getResultList();
	  	} else {
	  		List<AllotmentReport> lists = new ArrayList<AllotmentReport>();
	  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
	  		return lists;
	  	}
		// END Count the records
	  	
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
	
	@Override
	public TowerDashboard getTowerdashboard(String whereCondition) {
		TowerDashboard alotMIS = new TowerDashboard();
		Session session = this.sessionFactory.getCurrentSession();
		 
		//Tower - 1
		Query t1BHK1 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and c.propstrength__tower__c = 'a2F2s000000csXDEAY'  group by c.propstrength__unit_type__c ");
		
		Query t1BHK2 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and c.propstrength__tower__c = 'a2F2s000000csXDEAY'  group by c.propstrength__unit_type__c ");
		
		Query t1BHK3 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and c.propstrength__tower__c = 'a2F2s000000csXDEAY'  group by c.propstrength__unit_type__c ");
		
		
		//Tower - 2
		Query t2BHK1 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and c.propstrength__tower__c = 'a2F2s000000csXEEAY'  group by c.propstrength__unit_type__c ");
		
		Query t2BHK2 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and c.propstrength__tower__c = 'a2F2s000000csXEEAY'  group by c.propstrength__unit_type__c ");
		
		Query t2BHK3 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and c.propstrength__tower__c = 'a2F2s000000csXEEAY'  group by c.propstrength__unit_type__c ");
		
		
		//Tower - 3
		Query t3BHK1 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and c.propstrength__tower__c = 'a2F2s000000csXFEAY'  group by c.propstrength__unit_type__c ");
		
		Query t3BHK2 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and c.propstrength__tower__c = 'a2F2s000000csXFEAY'  group by c.propstrength__unit_type__c ");
		
		Query t3BHK3 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and c.propstrength__tower__c = 'a2F2s000000csXFEAY'  group by c.propstrength__unit_type__c ");
		
		//Tower - 4
		Query t4BHK1 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and c.propstrength__tower__c = 'a2F2s000000csXGEAY'  group by c.propstrength__unit_type__c ");
		
		Query t4BHK2 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and c.propstrength__tower__c = 'a2F2s000000csXGEAY'  group by c.propstrength__unit_type__c ");
		
		Query t4BHK3 = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c   "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and c.propstrength__tower__c = 'a2F2s000000csXGEAY'  group by c.propstrength__unit_type__c ");
		
		 
		//Tower 1
		if(t1BHK1.getResultList().size()>0) {
			alotMIS.setT1BHK1(t1BHK1.getResultList().get(0).toString());
		} else { alotMIS.setT1BHK1("0"); }
		
		if(t1BHK2.getResultList().size()>0) {
			alotMIS.setT1BHK2(t1BHK2.getResultList().get(0).toString());
		} else { alotMIS.setT1BHK2("0"); }
		 
		if(t1BHK3.getResultList().size()>0) {
			alotMIS.setT1BHK3(t1BHK3.getResultList().get(0).toString());
		} else { alotMIS.setT1BHK3("0"); }
		//Tower 2
		if(t2BHK1.getResultList().size()>0) {
			alotMIS.setT2BHK1(t2BHK1.getResultList().get(0).toString());
		} else { alotMIS.setT2BHK1("0"); }
		
		if(t2BHK2.getResultList().size()>0) {
			alotMIS.setT2BHK2(t2BHK2.getResultList().get(0).toString());
		} else { alotMIS.setT2BHK2("0"); }
		 
		if(t2BHK3.getResultList().size()>0) {
			alotMIS.setT2BHK3(t2BHK3.getResultList().get(0).toString());
		} else { alotMIS.setT2BHK3("0"); }		
		// Tower 3
		if(t3BHK1.getResultList().size()>0) {
			alotMIS.setT3BHK1(t3BHK1.getResultList().get(0).toString());
		} else { alotMIS.setT3BHK1("0"); }
		
		if(t3BHK2.getResultList().size()>0) {
			alotMIS.setT3BHK2(t3BHK2.getResultList().get(0).toString());
		} else { alotMIS.setT3BHK2("0"); }
		 
		if(t3BHK3.getResultList().size()>0) {
			alotMIS.setT3BHK3(t3BHK3.getResultList().get(0).toString());
		} else { alotMIS.setT3BHK3("0"); }		
		// Tower 4
		if(t4BHK1.getResultList().size()>0) {
			alotMIS.setT4BHK1(t4BHK1.getResultList().get(0).toString());
		} else { alotMIS.setT4BHK1("0"); }
		
		if(t4BHK2.getResultList().size()>0) {
			alotMIS.setT4BHK2(t4BHK2.getResultList().get(0).toString());
		} else { alotMIS.setT4BHK2("0"); }
		 
		if(t4BHK3.getResultList().size()>0) {
			alotMIS.setT4BHK3(t4BHK3.getResultList().get(0).toString());
		} else { alotMIS.setT4BHK3("0"); }
		
		return alotMIS;
	}
	
	
	@Override
	public FacingDashboard getFacingdashboard(String whereCondition) {
		FacingDashboard alotMIS = new FacingDashboard();
		Session session = this.sessionFactory.getCurrentSession();
		 
		Query city1bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and property_facing__c in ('City','Podium') group by c.propstrength__unit_type__c ");
		
		Query city2bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and property_facing__c in ('City','Podium') group by c.propstrength__unit_type__c ");
		
		Query city3bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and property_facing__c in ('City','Podium') group by c.propstrength__unit_type__c ");

		Query garden1bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '1 BHK' and property_facing__c = 'Garden' group by c.propstrength__unit_type__c ");
		
		Query garden2bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '2 BHK' and property_facing__c = 'Garden' group by c.propstrength__unit_type__c ");
		
		Query garden3bhk = session.createNativeQuery("SELECT count(c.propstrength__unit_type__c) FROM salesforce.gpl_cs_balance_details a "
				+ " INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND propstrength__status__c	= 'Closed Won' "
				+ " INNER JOIN salesforce.propstrength__property__c c ON c.sfid = b.propstrength__property__c "
				+ " where a.project_sfid = '"+whereCondition+"' and a.isactive = 'A' and c.propstrength__unit_type__c = '3 BHK' and property_facing__c = 'Garden' group by c.propstrength__unit_type__c ");
		
		
		 
		if(city1bhk.getResultList().size()>0) {
			alotMIS.setCity1bhk(city1bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setCity1bhk("0");
		}
		
		if(city2bhk.getResultList().size()>0) {
			alotMIS.setCity2bhk(city2bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setCity2bhk("0");
		}
		
		if(city3bhk.getResultList().size()>0) {
			alotMIS.setCity3bhk(city3bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setCity3bhk("0");
		}
		
		if(garden1bhk.getResultList().size()>0) {
			alotMIS.setGarden1bhk(garden1bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setGarden1bhk("0");
		}
		
		if(garden2bhk.getResultList().size()>0) {
			alotMIS.setGarden2bhk(garden2bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setGarden2bhk("0");
		}
		
		if(garden3bhk.getResultList().size()>0) {
			alotMIS.setGarden3bhk(garden3bhk.getResultList().get(0).toString());
		} else {
			alotMIS.setGarden3bhk("0");
		}
		
		return alotMIS;
	}
	
	
	
	@Override
	public List<UnitFacingCount> getUnitFacingCount(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<UnitFacingCount> authors=null;
		
		Query q = session.createNativeQuery("SELECT row_number() OVER () AS id, "
				
				+ " CASE WHEN a.propstrength__unit_type__c IS NULL THEN '-' ELSE a.propstrength__unit_type__c END as propstrength__unit_type__c,  "
				+ " CASE WHEN a.property_facing__c IS NULL THEN '-' ELSE a.property_facing__c END as property_facing__c, "
				+ " CASE WHEN b.count IS NULL THEN 0 ELSE b.count END as sold, "
				+ " CASE WHEN a.count IS NULL THEN 0 ELSE a.count END as total  "
				+ " FROM salesforce.vw_unit_facing_count a "
				
				//+ " LEFT JOIN salesforce.vw_offered_unit_facing_count b  "
				
				+ " LEFT JOIN salesforce.vw_offered_unit_facing_count b  "
				
				+ " ON a.propstrength__unit_type__c = b.propstrength__unit_type__c  "
				+ " AND a.property_facing__c = b.property_facing__c "
				//+ " AND b.project_sfid = '"+projectSFID+"' and b.isactive = 'A'  "
				
				+ " AND b.project_sfid = '"+projectSFID+"' AND b.propstrength__property_alloted_through_offer__c = true AND b.propstrength__active__c = true  "
				
				+ " where a.project18digit__c = '"+projectSFID+"'  AND a.propstrength__active__c = true order by a.propstrength__unit_type__c,  a.property_facing__c ASC ", UnitFacingCount.class);

		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
	@Override
	public List<UnitTowerCount> getUnitTowerCount(String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<UnitTowerCount> authors=null;
		
		Query q = session.createNativeQuery("SELECT row_number() OVER () AS id, "
				
				+ " CASE WHEN a.propstrength__unit_type__c IS NULL THEN '-' ELSE a.propstrength__unit_type__c END as propstrength__unit_type__c,  "
				+ " CASE WHEN a.tower_name__c IS NULL THEN '-' ELSE a.tower_name__c END as tower_name__c, "
				+ " CASE WHEN b.count IS NULL THEN 0 ELSE b.count END as sold, "
				+ " CASE WHEN a.count IS NULL THEN 0 ELSE a.count END as total  "
				+ " FROM salesforce.vw_unit_tower_count a "
				
				//+ " LEFT JOIN salesforce.vw_offered_unit_tower_count b  "
				+ " LEFT JOIN salesforce.vw_offered_unit_tower_count b  "
				
				+ " ON a.propstrength__unit_type__c = b.propstrength__unit_type__c  "
				+ " AND a.tower_name__c = b.tower_name__c "
				+ " AND b.project_sfid = '"+projectSFID+"' AND b.propstrength__property_alloted_through_offer__c = true AND b.propstrength__active__c = true  "
				+ " where a.project18digit__c = '"+projectSFID+"'  AND a.propstrength__active__c = true order by a.propstrength__unit_type__c,  a.tower_name__c ASC ", UnitTowerCount.class);

		authors = q.getResultList();
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	private List<AllotmentReport> getlist(List<AllotmentReport> list,String msg, String count){
		List<AllotmentReport> lists= list;
		if(lists.size()==0) {
			AllotmentReport getMisReport=	new AllotmentReport();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
	
}	