package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.KYCApplicantDtlDao;
import com.godrej.properties.model.AppOfferContact;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.EOIDataVW;
import com.godrej.properties.model.KYCApplicantDtl;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;

@Repository("kYCapplicantDtlDao")
public class KYCApplicantDtlDaoImpl extends AbstractDao<Integer, KYCApplicantDtl> implements KYCApplicantDtlDao{
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<KYCApplicantDtl> getApplicantData(String enqName, String contactSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<KYCApplicantDtl> authors=null;
		
		//by A - As prakash said, there no need to filter with admin approval status, get only active records 
		Query q = session.createNativeQuery("SELECT * "
				+ " FROM salesforce.nv_eoi_applicant_data "
				//+ " INNER JOIN salesforce.contact b ON a.propstrength__applicant_acc__c = b.sfid  "
				+ " where enquiry_id = '"+enqName+"' and isactive = 'Y' order by applicanttype ", KYCApplicantDtl.class);
		
		//isactive (Y)
		//kycapproval_status (Y)
		
		//order by b.id
		authors = q.getResultList();
		
		 
		
		
		if (authors != null && !authors.isEmpty() ) {
			return authors;
		} else {
			List<KYCApplicantDtl> listData=null;
			
			Query query = session.createNativeQuery("SELECT "
					
					//+ " salutation, "
					
					+ " mailing_street1__c, "
					+ " mailing_street2__c, "
					//+ " mailing_street3__c, "
					+ " mailing_city__c, "
					+ " mailing_state__c, "
					
					+ " country__c, "
					+ " postal_code__c, "
					
					+ " residential_street1__c, "
					+ " residential_street2__c, "
					//+ " residential_street3__c, "
					+ " residential_city__c, "
					+ " residential_post_code__c, "
					+ " residential_state__c, "
					+ " residential_country__c, "
					+ " propstrength__resident_status__c, "
					
					+ " nationality_a__c, "
					//+ " passport_no__c,  "
					+ " id,  "
					+ " name, "
					+ " birthdate, "
					//+ " birthday__c, "
					+ " propstrength__income_tax_permanent_account_no__c, "
					//+ " aadhar_card_no__c, "
					+ " email, "
					+ " mobile_number__c "
					+ " FROM salesforce.contact "
					+ " where sfid = '"+contactSFID+"'  ", AppOfferContact.class);
			
			
			
			
			listData = query.getResultList();
			
			//listData.size();
			
			if (listData != null && !listData.isEmpty() ) {
				return listData;
			} else {
				return null;
			}
		}
		
		//return null;
	}

	@SuppressWarnings("resource")
	@Override
	public List<EOIData> getKYCData(String userid, String projectid) {
		Session session = sessionFactory.getCurrentSession();
		
		String whereCondintion=" ";
		if(userid!=null && userid.length()>0)
			whereCondintion=" where userid ="+userid+"";
		if(userid!=null && userid.length()>0 && projectid!=null)
			whereCondintion=" where userid ="+userid+" and project_sfid='"+projectid+"'";
		else
			whereCondintion=" where project_sfid='"+projectid+"'";
		/* Changed by Vivek Birdi
		   Picking up data from view instead of table
		  */
		@SuppressWarnings("unchecked")
/*		List<EOIData> list = session.createQuery(" from EOIData "+whereCondintion+"").list();*/
		List<EOIData> list = session.createQuery(" from EOIDataVW "+whereCondintion+"").list();
		if (list.size() > 0)
			return list;
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EOIDataVW> getKYCDataVW(String userid, String projectid) {
		Session session = sessionFactory.getCurrentSession();
		//commented by Satheesh K. - Creating native query instead of view
		//Date : 27-11-2020
		//Reason: Query Optimization
		/*String whereCondintion=" ";
		if(userid!=null && userid.length()>0)
			whereCondintion=" where (userid ="+userid+" or createdoffer_userid="+userid+")";
		if(userid!=null && userid.length()>0 && projectid!=null)//(a.userid=594 or balnce.userid=594)
			whereCondintion=" where (userid ="+userid+" or createdoffer_userid="+userid+") and project_sfid='"+projectid+"'";
		else
			whereCondintion=" where project_sfid='"+projectid+"'";
		
		return session.createQuery(" from EOIDataVW "+whereCondintion+"").list();*/
		List<EOIDataVW> authors =null;
		
		String whereCondintion=" ";
		if(userid!=null && userid.length()>0)
			whereCondintion=" where (userid ="+userid+" or createdoffer_userid="+userid+")";
		if(userid!=null && userid.length()>0 && projectid!=null)//(a.userid=594 or balnce.userid=594)
			whereCondintion=" where (userid ="+userid+" or createdoffer_userid="+userid+") and project_sfid='"+projectid+"'";
		else
			whereCondintion=" where project_sfid='"+projectid+"'";
		
		Query q = session.createNativeQuery("SELECT row_number() OVER (ORDER BY a.id) AS id,a.enquiryid,a.application_name,a.phone_number,a.issubmitted,"
				+ "a.userdocid,a.userid,a.kyclink,a.kycapproval_status,a.kycreject_comment,a.project_sfid,"
				+ "b.sfid AS enquirysfid,b.propstrength__primary_contact__c AS contactsfid,c.name AS offername,"
				+ "c.sfid AS offersfid,balnce.description,"
				+ "( SELECT gpl_cs_balance_details.costsheet_path FROM salesforce.gpl_cs_balance_details WHERE gpl_cs_balance_details.offer_sfid = c.sfid AND gpl_cs_balance_details.isactive = 'A') AS costsheet_path,"
				+ "c.property_name1__c,sum(e.propstrength__amount__c) AS totalamount,balnce.userid AS createdoffer_userid,"
				+ "mstuser.user_name AS offercreatedname,kycapproval.user_name AS kycapproval_name,application.name AS booking_name,application.propstrength__status__c AS booking_status"
				+ " FROM salesforce.nv_eoi_form a"
				+ " LEFT JOIN salesforce.propstrength__request__c b ON a.enquiryid = b.name"
				+ " LEFT JOIN salesforce.propstrength__offer__c c ON b.sfid = c.propstrength__request__c AND c.propstrength__status__c = 'Closed Won' AND c.propstrength__status__c IS NOT NULL"
				+ " LEFT JOIN salesforce.propstrength__prepayment_received__c e ON c.sfid = e.propstrength__offer__c"
				+ " LEFT JOIN salesforce.gpl_cs_balance_details balnce ON c.sfid = balnce.offer_sfid AND balnce.isactive = 'A'"
				+ " LEFT JOIN salesforce.mst_user mstuser ON mstuser.user_id = balnce.userid"//::integer
				+ " LEFT JOIN salesforce.propstrength__application_booking__c application ON c.sfid = application.propstrength__offer__c"
				+ " LEFT JOIN salesforce.mst_user kycapproval ON kycapproval.user_id = a.approve_reject_by"
				+ " WHERE a.enquiryid IS NOT NULL and a.project_sfid='a1l6F000002X6IOQA0'"
				+ " GROUP BY a.id, a.enquiryid, a.application_name, a.phone_number, a.issubmitted, a.userdocid, a.userid, a.kyclink, a.kycapproval_status, a.kycreject_comment, a.project_sfid, b.sfid, b.propstrength__primary_contact__c, c.name, c.sfid, c.property_name1__c, balnce.userid, mstuser.user_name, application.name, kycapproval.user_name, application.propstrength__status__c, balnce.description ", EOIDataVW.class);
		
		authors = (List<EOIDataVW>)q.getResultList();
		log.info("KYC List Admin Query ::: {}", q);
		if(authors.size()>0)
		{
			return authors;
		}
		return authors;
	}
	
	@Override
	public EOIData getKYCStatus(String enquiryName, String projectid) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<EOIData> list = session.createQuery(" from EOIData where enquiryid ='"+enquiryName+"'").list();
		if (list.size() > 0)
			return list.get(0);
		return null;
	}
}