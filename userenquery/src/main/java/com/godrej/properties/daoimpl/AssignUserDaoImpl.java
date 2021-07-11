package com.godrej.properties.daoimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.AssignUserDao;
import com.godrej.properties.dto.VW_UserMasterDao;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.EOIPreferenceDtl;
import com.godrej.properties.model.Vw_UserMaster; 

@Repository("assignUserDao")
public class AssignUserDaoImpl extends AbstractDao<Integer, AssignedUser> implements AssignUserDao {


	@Autowired
	private SessionFactory sessionFactory;
	public AssignUserDaoImpl() {
		
	}
	  
	 

	/*@Override
	public List<AssignedUser> getassignedusers(String user_id,String projectId,String fromdate) {
		Session session = this.sessionFactory.getCurrentSession();
		List<AssignedUser> list =null;
		 list =session.createQuery(" from AssignedUser where  window_assign = '"+user_id
				 +"'    and projectname='"+projectId+"'  and Date(created)='"+fromdate+"' order by isdone asc").list(); // and projectname='"+projectId+"'
	 
		if(list.size()>0)
			return list;
		return null;
	}*/
	
	
	@Override
	public List<AssignedUser> getassignedusers(String user_id,String projectId,String fromdate,String todate, String source) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<AssignedUser> list=null;
		
		/*
		 * Query q =
		 * session.createNativeQuery("SELECT * FROM salesforce.gpl_cs_eoi_header_dtl " +
		 * " where enq_sfid = '"+enqSfid+"' order by gpl_cs_eoi_header_dtl ",
		 * AssignedUser.class);
		 */
		String whereCondition = "";
		
		if (source.equals("followup")) {
			Date date = new Date();
			String todayDate= "";
			try {
				todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			whereCondition = "a.isactive = 'Y' AND window_assign = '"+user_id+"' and projectname= '"+projectId+"' and Date(nvreq.followdate) between '"+todayDate+"' and '"+todayDate+"' order by isdone asc";
		} else {
			whereCondition = "a.isactive = 'Y' AND window_assign = '"+user_id+"' and projectname= '"+projectId+"' and Date(a.created) between '"+fromdate+"' and '"+todate+"' order by isdone asc";
		}
		
		
		Query q = session.createNativeQuery(" SELECT row_number() OVER (ORDER BY a.nv_token_id) AS row_number,  "
				+ " a.projectname, a.nv_token_id, concat(a.type, a.queue) AS token_no, c.name, a.starteddate, a.mobileno, a.window_assign, a.created AS created, "
			+ " CASE "
				+ " WHEN a.isdone IS NULL THEN '' "
				+ " WHEN a.isdone = 'S' THEN 'Started' "
				+ " ELSE 'Attended' "
			+ " END AS isdone, "
			+ " req.priority_no__c, a.createdby, "
			+ " COALESCE(req.closing_manager_name__c, '') AS closing_manager_name__c, bdetails.offer_sfid AS offersfid, "
			+ " CASE "
			+ " WHEN offername.name IS NULL THEN '' "
				+ " ELSE offername.name "
			+ " END AS offername, "
			+ " btrim(a.countrycode) AS countrycode, "
			+ " nvreq.followtype, "
			+ " nvreq.followdate"
			
			+ " FROM salesforce.nv_token a "
			+ " LEFT JOIN salesforce.propstrength__request__c req ON  CAST(a.enquiry_18 as integer) = req.id "
			+ " LEFT JOIN salesforce.nv_hc_enquiry nvreq ON  CAST(a.enquiry_18 as integer) = nvreq.enquiry_id "
			+ " LEFT JOIN salesforce.contact c ON req.propstrength__primary_contact__c = c.sfid "
			+ " LEFT JOIN salesforce.gpl_cs_balance_details bdetails ON req.sfid = bdetails.enquiry_sfid AND bdetails.isactive = 'A' "
			+ " LEFT JOIN salesforce.propstrength__offer__c offername ON bdetails.offer_sfid = offername.sfid AND offername.propstrength__status__c = 'Closed Won' "
			//+ " WHERE a.isactive = 'Y' AND window_assign = '"+user_id+"' and projectname= '"+projectId+"' and Date(a.created) between '"+fromdate+"' and '"+todate+"' order by isdone asc ", AssignedUser.class);
			+ " WHERE "+whereCondition+" ", AssignedUser.class);
		
		//order by b.id
		list = q.getResultList();
		
		System.out.println("Final Size::"+list.size());
		
		if (list.size() > 0)
			return list;

		return null;
	}
	
	
	
}
