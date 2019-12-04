package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.ApplicantDtlDao;
import com.godrej.properties.model.ApplicantDtl;

@Repository("applicantDtlDao")
public class ApplicantDtlDaoImpl extends AbstractDao<Integer, ApplicantDtl> implements ApplicantDtlDao{
	@Autowired
	private SessionFactory sessionFactory;	
	
	@Override
	public List<ApplicantDtl> getApplicantData(String applicationSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<ApplicantDtl> authors=null;
		
		
		
		
		Query q = session.createNativeQuery("SELECT b.salutation, b.mailing_street1__c, b.mailing_street2__c, b.mailing_street3__c, b.mailing_city__c, b.mailing_state__c, b.country__c, b.postal_code__c, b.residential_street1__c, b.residential_street2__c, b.residential_street3__c, b.residential_city__c, b.residential_post_code__c, b.residential_state__c, b.residential_country__c, b.propstrength__resident_status__c, b.nationality_a__c, b.passport_no__c, a.propstrength__sharing_ratio__c, b.id, a.propstrength__type__c, b.name, b.birthdate, b.birthday__c, b.propstrength__income_tax_permanent_account_no__c, b.aadhar_card_no__c, b.email, b.mobile_number__c "
				+ " FROM salesforce.propstrength__applicant_detail__c a "
				+ " INNER JOIN salesforce.contact b ON a.propstrength__applicant_acc__c = b.sfid  "
				+ " where a.propstrength__application__c = '"+applicationSfid+"' order by a.propstrength__type__c ", ApplicantDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
}