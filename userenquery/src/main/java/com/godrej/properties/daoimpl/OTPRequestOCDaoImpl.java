package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.OTPRequestOCDao;
import com.godrej.properties.model.OTPRequestOC;


@SuppressWarnings("unchecked")
@Repository("oTPRequestOCDao")
public class OTPRequestOCDaoImpl extends AbstractDao<Integer, OTPRequestOC> implements OTPRequestOCDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	public void insertOTPRqst(OTPRequestOC action) {
		persist(action);
	}
	
	
	public OTPRequestOC validateOTP(String OTPValidate, String herokuEnqId) {	 
		
		Session session = this.sessionFactory.getCurrentSession();
		List<OTPRequestOC> list  =session.createQuery(" FROM  OTPRequestOC where enquiry_id = '"+herokuEnqId+"' and isactive = 'A' and otp = '"+OTPValidate+"' ").list();
		
		if(list.size()>0)
		{
			return list.get(0);
		}
		
		return null;		
	}



	@Override
	public void updatePreviousOtp(OTPRequestOC updateOTP) {
		Session session = this.sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("UPDATE OTPRequestOC SET isactive = '"+updateOTP.getIsactive()+"'  WHERE  enquiry_id = '"+updateOTP.getEnquiry_id()+"' ");
		
		query.executeUpdate();
		
	}
	
	
}