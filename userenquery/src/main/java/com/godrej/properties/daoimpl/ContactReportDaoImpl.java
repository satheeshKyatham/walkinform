package com.godrej.properties.daoimpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.godrej.properties.dao.AAbstractDao;
import com.godrej.properties.dao.ContactReportDao;
import com.godrej.properties.model.ContactReport;

/**
 * 
 * @author Varsha Patil
 *
 */
@Repository
@Transactional
public class ContactReportDaoImpl extends AAbstractDao<ContactReport> implements ContactReportDao{

	@Override
	public ContactReport insertContactReport(ContactReport enq) {
		persist(enq);
		return enq;
	}

	/*@Override
	public ContactReport update(ContactReport entity) {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public ContactReport findById(Integer id) {		
		return findOne(id);
	}

	@Override
	public ContactReport getContactReportById(Integer id) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT cr FROM ContactReport cr ");
		/* jpql.append(" LEFT JOIN FETCH cr.contact cid ");*/
		/* jpql.append(" LEFT JOIN FETCH cr.contactSfid csfid ");*/
		 jpql.append(" where cr.contactReportId=:contactReportId ");
		 Map<String, Object> params=new HashMap<>();
		 params.put("contactReportId", id);
	 return getSingleEntity(jpql.toString(), params);
	}
	
	@Override
	public void updateContactSfidToCustomContact() {
		StringBuilder jpql=new StringBuilder();
		jpql.append(" UPDATE salesforce.nv_hc_contact hc ")
		.append(" SET contact_sfid=a.sfid ,isupdated='Y' ")
		.append(" FROM (select sfid,External_Contact_ID__c from salesforce.contact c) a where a.External_Contact_ID__c=hc.nv_hc_contact_id and a.sfid is not null and hc.isupdated='N'  ");
		updateByNative(jpql.toString(), null);	
	}

	@Override
	public void updateById(Map<String, Object> params) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" Update ContactReport cr ")
		 .append(" set cr.contactId=:contactId ");
		 /*.append(" set cr.contact.contactId=:contactId ");*/
		 jpql.append(" where cr.contactReportId=:contactReportId ");
		 Query query=getSession().createQuery(jpql.toString());
		 query.setParameter("contactId", params.get("contactId"));
		 query.setParameter("contactReportId", params.get("contactReportId"));
		 query.executeUpdate();			
	}
	
	@Override
	public void updateRatingSFDC(ContactReport contcat) {
		if(contcat.getContactSfid()!=null && contcat.getContactSfid().length()>0)
		{
			System.out.println("Rating query Start :"+new Date());
		 StringBuilder jpql=new StringBuilder();
			jpql.append(" update salesforce.propstrength__request__c c set rating__c=b.customer_classification from( ")
			.append(" select request__c.name,request__c.rating__c,hc_contcat.customer_classification,request__c.id,request__c.sfid,hc_contcat.contact_id,hc_contcat.contact_sfid from salesforce.nv_hc_contact hc_contcat ")
			.append(" inner join salesforce.propstrength__request__c request__c on(hc_contcat.contact_id=request__c.external_contact_id__c) ")
			.append(" where  hc_contcat.contact_sfid='"+contcat.getContactSfid()+"' ) b where b.sfid=c.sfid and b.contact_sfid='"+contcat.getContactSfid()+"'");
			System.out.println("Rating Update Query::::"+jpql);
			updateByNative(jpql.toString(), null);
			System.out.println("Rating query END :"+new Date());
		}
	}
	
	@Override
	public void updateContactONSFDC(ContactReport contcat) {
		if(contcat.getContactSfid()!=null && contcat.getContactSfid().length()>0)
		{
			System.out.println("SFDC query Start :"+new Date());
		 StringBuilder jpql=new StringBuilder();
		 //current_residence_ownership__c--contcat.getCurrentResidenceOwnership()
			jpql.append(" Update salesforce.contact set current_residence_type__c ='"+contcat.getCurrentResidenceType()+"', current_residence_ownership__c='"+contcat.getCurrentResidenceOwnership()+"'");
			jpql.append(" where sfid='"+contcat.getContactSfid()+"'");//contcat.getCurrentResidenceType()

			updateByNative(jpql.toString(), null);
			System.out.println("SFDC query END :"+new Date());
		}
	}

	@Override
	public ContactReport getContactReportData(Integer id) {
		 StringBuilder jpql=new StringBuilder();
		 jpql.append(" SELECT cr FROM ContactReport cr ");
		 jpql.append(" where cr.contactId=:contactId ");
		 Map<String, Object> params=new HashMap<>();
		 params.put("contactId", id);
	 return getSingleEntity(jpql.toString(), params);
	}
}
