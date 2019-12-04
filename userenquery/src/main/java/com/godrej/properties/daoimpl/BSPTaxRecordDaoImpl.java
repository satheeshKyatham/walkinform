package com.godrej.properties.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.BSPTaxRecordDao;
import com.godrej.properties.dao.PaymentPlanLineItemDao;
import com.godrej.properties.model.BSPTaxRecord;
import com.godrej.properties.model.PaymentPlanLineItem;
import com.godrej.properties.model.PaymentPlanWithOtherCharge;

@Repository("bSPTaxRecordDao")
public class BSPTaxRecordDaoImpl extends AbstractDao<Integer, BSPTaxRecord> implements BSPTaxRecordDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BSPTaxRecord> getBSPTax(){
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<BSPTaxRecord> authors =null;
			
			Query q = session.createNativeQuery("SELECT b.id, b.propstrength__tax_percentage__c, b.propstrength__new_tax_percentage__c, b.propstrength__pmay_gst__c, b.propstrength__new_pmay_gst__c "
					+ " FROM salesforce.propstrength__service_tax_mapping__c a "
					+ " LEFT JOIN salesforce.propstrength__tax_record__c b ON a.propstrength__tax_record__c = b.sfid  "
					+ " where a.propstrength__service_category__c = 'Basic Sale Price' and b.propstrength__tax_type__c in ('CGST', 'SGST') ", BSPTaxRecord.class);
			authors = (List<BSPTaxRecord>)q.getResultList();
			
		if(authors.size()>0)
		{
			return authors;
		}
		return null;
	}
	
}