package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIEnquiryDao;
import com.godrej.properties.model.CoApplicant;
import com.godrej.properties.model.EOIData;
import com.godrej.properties.model.Token;
import com.godrej.properties.model.VWEOILimitAmount;
import com.google.gson.JsonElement;




@Repository("userEOIDao")
public class EOIEnquiryDaoImpl extends AbstractDao<Integer, EOIData> implements EOIEnquiryDao {


	@Autowired
	private SessionFactory sessionFactory;
	public EOIEnquiryDaoImpl() {
		
	}
	
	@Override
	public List<EOIData> findMobileNoExist(String mobileno) {
		 
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<EOIData> list =session.createQuery(" from EOIData where phone_number like '%"+mobileno+"'").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void createQRCode(String mobileno, String filePath, String code_numeric, String code_str) {
		getSession().createSQLQuery("Update  nv_eoi_form set barcode_str='"+code_str+"',barcode_numeric='"+code_numeric+"',barcode_location='"+filePath+"' where phone_number like '%"+mobileno+"'").executeUpdate();
		
	}

	@Override
	public List<EOIData> searchUserQRCode(String code) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<EOIData> list =session.createQuery(" from EOIData where barcode_numeric = '"+code+"'").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	@Override
	public void UpdateToken(Token token) {
		getSession().createSQLQuery("Update  salesforce.nv_eoi_form set token_no='"+token.getNv_token_id()+"' where phone_number like '%"+token.getMobileno()+"' and project_sfid='"+token.getProjectname()+"'").executeUpdate();
		
	}

	@Override
	public List<EOIData> getAllData() {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<EOIData> list =session.createQuery(" from EOIData ").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	@Override
	public List<CoApplicant> getCoapplicantData(String enqsfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<CoApplicant> list =session.createQuery(" from CoApplicant where Enquiry_ID ='"+enqsfid+"' and isactive= 'Y' and issync='Y' and kycapproval_status='Y' order by applicantType").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	@Override
	public VWEOILimitAmount getEOITokenType(String enqsfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<VWEOILimitAmount> list =session.createQuery(" from VWEOILimitAmount where enq_sfid='"+enqsfid+"'").list();
		if(list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<EOIData> findMobileNoExistEOIForm(String mobileno,String project_sfid) {
		 
		Session session = this.sessionFactory.getCurrentSession();	
		@SuppressWarnings("unchecked")
		List<EOIData> list =session.createQuery(" from EOIData where phone_number like '%"+mobileno+"' and project_sfid ='"+project_sfid+"'").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

	

	@Override
	public List<EOIData> findMobileNoExist(String mobileno, String project_sfid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
