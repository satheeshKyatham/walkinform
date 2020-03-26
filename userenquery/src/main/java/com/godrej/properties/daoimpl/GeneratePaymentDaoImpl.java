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
import com.godrej.properties.dao.GeneratePaymentDao;
import com.godrej.properties.model.GeneratePayment;

@SuppressWarnings("unchecked")
@Repository("generatePaymentDao")
public class GeneratePaymentDaoImpl extends AbstractDao<Integer, GeneratePayment> implements GeneratePaymentDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean insertPaymentDtl(List<GeneratePayment> pymtDtl) {
	try {
			batchPersist(pymtDtl);
			return true;
		} catch (Exception e) {
			Log.info("Inset Payment Request Error:- ",e);
			return false;
		}
	}	
	
	@Override
	public List<GeneratePayment> getPaymentRecord(String enqSfid, String projectSFID) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		try {
			List<GeneratePayment> authors=null;
			
			Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cc_payment_request "
					+ " where enquiry_sfid = '"+enqSfid+"' AND  project_sfid = '"+projectSFID+"' AND isactive = 'Y' order by id ", GeneratePayment.class);
			authors = q.getResultList();
			
			if(authors!=null) {
				if (authors.size() > 0) {
					return authors;
				}	
			}
		} catch (Exception e) {
			Log.info("Get Payment Request Error:- ",e);
		}
		return null;
	} 

	@Override
	public GeneratePayment getCCPaymentData(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<GeneratePayment> payment = session.createQuery(" from GeneratePayment where "
				+ "id = "+id+" and isactive='Y'").list();
		if (payment.size() > 0)
			return payment.get(0);
		return null;
	}
}