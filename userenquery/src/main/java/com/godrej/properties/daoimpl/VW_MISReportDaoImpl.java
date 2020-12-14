package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.VW_MISReportDao;
import com.godrej.properties.model.EOIDataVW;
import com.godrej.properties.model.Vw_MISReport;


@Repository("vW_MISReportDao")
public class VW_MISReportDaoImpl extends AbstractDao<Integer, Vw_MISReport> implements VW_MISReportDao{

	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Vw_MISReport> getUserProjectList(String projectid,String userid, String fromDate, String toDate, String userVerticals) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Vw_MISReport> list =null;
		
		String condition = "";
		
		if (userVerticals != null) {
			condition = " and verticle__c in ("+userVerticals+") ";
		} else {
			condition = "";
		}
		
		
		
		String SQL_QUERY = "";
		
		if(((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)) && (userid !=null && userid.length()>0) )
			list =session.createQuery(" from Vw_MISReport where projectid= "+projectid+" and user_id="+userid+" and Date(created) between '"+fromDate+"' and '"+toDate+"' order by created desc ").list();
		else if((fromDate!=null && fromDate.length()>0) && (toDate!=null && toDate.length()>0)){
			SQL_QUERY = "SELECT COUNT(*) FROM Vw_MISReport where projectid in ("+projectid+") and Date(created) between '"+fromDate+"' and '"+toDate+"'" + condition;
		  	Query query = session.createQuery(SQL_QUERY);
		
		  	long row = 0L;
		  	for(Iterator it=query.iterate();it.hasNext();){
				row = (Long) it.next();
		  	}
		  	
		  	String strRowCount = Long.toString(row);
		  	
		  	if (row <= 5000) {
		  		list =session.createQuery(" from Vw_MISReport where projectid in ("+projectid+") and Date(created) between '"+fromDate+"' and '"+toDate+"' " +condition+ " order by projectname,created desc ").list();
		  		
		  		/*Query q = session.createNativeQuery(" SELECT e.sfid AS projectid,b.verticle__c,e.name AS projectname,concat(a.type, a.queue) AS tokenno, "
		  				+ " date(a.created) AS created,b.name AS enquiryname,c.mobilephone,concat(c.firstname, ' ', c.lastname) AS name,CASE WHEN c.email IS NULL THEN c.propstrength__email_id__c ELSE c.email END AS email,"
		  				+ " d.have_we_met_before,f.age_group AS age_a__c,concat(c.residential_street1__c, c.residential_street2__c, ' ', c.residential_street3__c, ' ', c.residential_city__c, ' ', c.residential_country__c, ' ', c.residential_post_code__c) AS residenceaddress,"
		  				+ " concat(f.office_address, ' ', f.office_city, ' ', f.office_pincode) AS officelocation,f.employment_status AS empstatus,c.company_name__c,d.purchase AS is_purchase_for_self_use_or_investment__c,"
		  				+ " d.budget,d.carpet_area_requirement,d.typology_requirement,b.walk_in_source__c,b.advertisement__c, b.id,g.name AS advertisementname,b.propstrength__broker_account__c,"
		  				+ " account.name AS brokername,f.current_residence_configuration,f.current_residence_ownership,d.source_of_funding,b.rating__c AS customer_classification,f.ethnicity,"
		  				+ " d.unit_availability,d.accompanied_by,d.deal_negotiation,d.construction_status,d.timeframe_to_book,d.enquirynoneditcomment,b.sourcing_managers__c,source.name__c AS sourcingname,source.email__c AS sourcingemail,"
		  				+ " b.closing_managers__c,closing.name__c AS closingname,closing.email__c AS closingemail,d.own_contribution_receipt,d.loan_eligibility,a.window_assign,"//,usr.user_name
		  				+ " CASE WHEN a.isdone IS NULL THEN 'No' ELSE 'Yes' END AS attended,d.cp_comments__c,date(d.followdate) AS followdate,d.followtype,"
		  				//+ " CASE WHEN usr.user_id IS NULL THEN 0 ELSE usr.user_id END AS user_id,
		  				+" d.trigger1,d.trigger2,d.barrier1,d.barrier2,b.lost_reason_c__c,c.designation__c,b.media_type__c,"
		  				+ " b.media_sub_type__c,d.is_revisit,d.lastvisitdate,"
		  				+ " CASE WHEN b.site_visit_done__c = 1 AND b.virtual_meeting_count__c = 1 THEN 'Site Visit & Virtual Meeting'"
		  				+ " WHEN b.site_visit_done__c = 1 THEN 'Site Visit' WHEN b.virtual_meeting_count__c = 1 THEN 'Virtual Meeting' ELSE '' END AS type_of_visit,'ff' as d4u_comments,"
		  				+ " (CASE WHEN (SELECT count(enquiry_id) from salesforce.nv_hc_enquiry_log where enquiry_id=b.id)>0"
		  				//+ " (CASE WHEN (SELECT count(enquiry_id) from salesforce.nv_hc_enquiry_log where enquiry_id=b.id)<=0"
		  				//+" THEN 'No Entry' ELSE 'Entry found' END)AS dd_test"//order by elog.createddate desc
//		  				+ " THEN (SELECT array_to_string(array_agg(elog.createddate || ':' ||elog.enquirynoneditcomment ),';') as enquirynoneditcomment FROM salesforce.nv_hc_enquiry_log elog WHERE elog.enquiry_id = b.id) ELSE 'No Entry' END)AS dd_test"//order by elog.createddate desc
//		  				+ " (SELECT array_to_string(array_agg((elog.createddate || ':-') || elog.enquirynoneditcomment ORDER BY elog.createddate DESC) filter (WHERE elog.enquirynoneditcomment is not null and elog.createddate is not null), e'\\n') AS array_to_string FROM salesforce.nv_hc_enquiry_log elog WHERE elog.enquiry_id = d.enquiry_id ) AS dd_test"
						//+ " array(SELECT elog.enquirynoneditcomment FROM salesforce.nv_hc_enquiry_log elog WHERE elog.enquiry_id = b.id ) AS dd_test"		  				
//+ " (SELECT array_to_string(array_agg(elog.enquirynoneditcomment ORDER BY elog.createddate DESC), ',') AS array_to_string FROM salesforce.nv_hc_enquiry_log elog WHERE elog.enquiry_id = b.id) AS d4u_comments"
		  				+ " logs.d4u_comments as dd_test"
		  				+ " FROM salesforce.nv_token a "
		  				+ " JOIN salesforce.propstrength__request__c b ON CAST(a.enquiry_18 as integer) = b.id"//e'\\n'
		  				+ " LEFT JOIN salesforce.contact c ON CAST(b.external_contact_id__c as integer) = c.id"
		  				+ " LEFT JOIN salesforce.nv_hc_enquiry d ON b.id = d.enquiry_id" + 
		  				"     LEFT JOIN salesforce.nv_hc_contact f ON c.id = f.contact_id AND f.isupdated = 'Y'" + 
		  				"     LEFT JOIN salesforce.nv_projects_c e ON e.project_18_digit__c = a.projectname" + 
		  				"     LEFT JOIN salesforce.vcc1__advertisement__c g ON g.sfid = b.advertisement__c" + 
		  				"     LEFT JOIN salesforce.account account ON b.propstrength__broker_account__c = account.sfid AND account.recordtypeid = '0126F000000uazCQAQ'" + 
		  				"     LEFT JOIN salesforce.case_escalation_users_detail__c source ON b.sourcing_managers__c = source.sfid" + 
		  				"     LEFT JOIN salesforce.case_escalation_users_detail__c closing ON b.closing_managers__c = closing.sfid" + 
		  				//"     LEFT JOIN salesforce.mst_user usr ON CAST(a.window_assign as integer) = usr.user_id AND usr.isactive = 'A'"+
		  				 " INNER JOIN (select distinct logs.enquiry_id,array_to_string(array_agg((logs.createddate || ':-') || logs.enquirynoneditcomment ORDER BY logs.createddate DESC),':') as d4u_comments  from salesforce.nv_hc_enquiry_log logs group by logs.enquiry_id ) logs on logs.enquiry_id=d.enquiry_id "
		  				 + "WHERE a.isactive = 'Y' and e.sfid in ('a1l6F000008DnniQAC') "//and a.nv_token_id=148043
		  				, Vw_MISReport.class);
		  		list = (List<Vw_MISReport>)q.getResultList();*/
		  	}else {
		  		List<Vw_MISReport> lists = new ArrayList<Vw_MISReport>();
		  		lists = getlist(lists,"MAX_LIMIT",strRowCount);
		  		return lists;
		  	}
		}else if(projectid!=null && projectid.length()>0) {
			SQL_QUERY = "SELECT COUNT(*) FROM Vw_MISReport where projectid in ("+projectid+")" + condition;
		  	Query query = session.createQuery(SQL_QUERY);
		
		  	long row = 0L;
		  	for(Iterator it=query.iterate();it.hasNext();){
				row = (Long) it.next();
		  	}
		  	
		  	String strRowCount = Long.toString(row);
		  	
		  	if (row <= 5000) {
		  		list =session.createQuery(" from Vw_MISReport where projectid in ("+projectid+") "+ condition +" order by projectname,created desc ").list();
		  	}else {
		  		List<Vw_MISReport> lists = new ArrayList<Vw_MISReport>();
		  		lists = getlist(lists,"MAX_LIMIT", strRowCount);
		  		return lists;
		  	}
		}else
			list =session.createQuery(" from Vw_MISReport ").list();
		if(list.size()>0)
			return list;
		return null;
	}
	
	private List<Vw_MISReport> getlist(List<Vw_MISReport> list,String msg, String count)
	{
		List<Vw_MISReport> lists= list;
		if(lists.size()==0) {
			Vw_MISReport getMisReport=	new Vw_MISReport();;
			getMisReport.setQry_count(count);
			getMisReport.setQry_msg(msg);
			lists.add(getMisReport);
		}
		return lists;
	}
	
	
}
