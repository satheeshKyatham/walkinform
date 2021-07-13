package com.godrej.properties.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.DashboardDao;
import com.godrej.properties.model.DashboardReport;

@Repository("dashboardDao")
public class DashboardDaoImpl implements DashboardDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public DashboardReport getDashboard(String projectSFID, String userid) {
		DashboardReport alotMIS = new DashboardReport();
		Session session = this.sessionFactory.getCurrentSession();
		
		
		Date date = new Date();
		String todayDate= "";
		try {
			todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		 
		Query todayAssigned = session.createNativeQuery("select count(nv_token_id) as todayAssigned FROM salesforce.nv_token where isactive = 'Y' AND window_assign = '"+userid+"' and projectname= '"+projectSFID+"' and Date(created) between '"+todayDate+"' and '"+todayDate+"'  ");
		
		Query totalPendingLeadISNull = session.createNativeQuery("select count(nv_token_id) as totalPendingLead FROM salesforce.nv_token where isactive = 'Y' AND window_assign = '"+userid+"' and projectname= '"+projectSFID+"' AND isdone IS NULL  ");
		
		Query totalPendingLeadISS = session.createNativeQuery("select count(nv_token_id) as totalPendingLead FROM salesforce.nv_token where isactive = 'Y' AND window_assign = '"+userid+"' and projectname= '"+projectSFID+"' AND isdone = 'S'  ");
		
		Query todayFollowup = session.createNativeQuery("Select count(a.nv_token_id) as todayFollowup FROM salesforce.nv_token a INNER JOIN salesforce.nv_hc_enquiry b ON  CAST(a.enquiry_18 as integer) = b.enquiry_id  where a.isactive = 'Y' AND a.window_assign = '"+userid+"' and a.projectname= '"+projectSFID+"' and Date(b.followdate) between '"+todayDate+"' and '"+todayDate+"'   ");
		
		Query totalPendingKYCApproval = session.createNativeQuery("SELECT count(*) FROM salesforce.nv_eoi_form  where issubmitted = 'Y' AND kycapproval_status is NULL AND userid = '"+userid+"' and project_sfid = '"+projectSFID+"' ");
		
		Query totalCreatedOffer = session.createNativeQuery("SELECT count(*) FROM salesforce.gpl_cs_balance_details a INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid where  a.project_sfid = '"+projectSFID+"' and userid= '"+userid+"' AND b.propstrength__status__c  = 'Closed Won'  ");
		
		Query totalBookingDone = session.createNativeQuery("SELECT count(*) FROM salesforce.gpl_cs_balance_details a INNER JOIN salesforce.propstrength__offer__c b ON a.offer_sfid = b.sfid AND b.propstrength__status__c = 'Closed Won'  INNER JOIN salesforce.propstrength__application_booking__c c ON c.propstrength__offer__c = b.sfid AND c.deal_status__c = 'Approved' AND c.propstrength__status__c = 'Deal Approved'  where  a.project_sfid = '"+projectSFID+"' and userid= '"+userid+"' ");
		
		
		alotMIS.setTodayAssigned(todayAssigned.getResultList().get(0) == null ? "0" : todayAssigned.getResultList().get(0).toString().toString());
		//alotMIS.setTotalPendingLead(totalPendingLead.getResultList().get(0) == null ? "0" : totalPendingLead.getResultList().get(0).toString().toString());
		alotMIS.setTodayFollowup(todayFollowup.getResultList().get(0) == null ? "0" : todayFollowup.getResultList().get(0).toString().toString());
		alotMIS.setTotalPendingKYCApproval(totalPendingKYCApproval.getResultList().get(0) == null ? "0" : totalPendingKYCApproval.getResultList().get(0).toString());
		alotMIS.setTotalCreatedOffer(totalCreatedOffer.getResultList().get(0) == null ? "0" : totalCreatedOffer.getResultList().get(0).toString());
		alotMIS.setTotalBookingDone(totalBookingDone.getResultList().get(0) == null ? "0" : totalBookingDone.getResultList().get(0).toString());
		
		Integer tpISNull = 0;
		Integer tpISS = 0;
		
		if (totalPendingLeadISNull.getResultList().get(0) == null ) {
			tpISNull = 0;
		} else {
			tpISNull = Integer.parseInt(totalPendingLeadISNull.getResultList().get(0).toString());
		}
		
		if (totalPendingLeadISS.getResultList().get(0) == null ) {
			tpISS = 0;
		} else {
			tpISS = Integer.parseInt(totalPendingLeadISS.getResultList().get(0).toString());
		}
		
		
		alotMIS.setTotalPendingLead(Integer.sum(tpISNull, tpISS));
		
		return alotMIS;
	}
}	