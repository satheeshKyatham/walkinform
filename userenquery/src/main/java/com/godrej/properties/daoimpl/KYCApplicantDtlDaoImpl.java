package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.KYCApplicantDtlDao;
import com.godrej.properties.model.AppOfferContact;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.EOIDataVW;
import com.godrej.properties.model.KYCApplicantDtl;

@Repository("kYCapplicantDtlDao")
public class KYCApplicantDtlDaoImpl extends AbstractDao<Integer, KYCApplicantDtl> implements KYCApplicantDtlDao{
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
		return session.createQuery(" from EOIDataVW "+whereCondintion+"").list();
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