package com.godrej.properties.daoimpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EnqJourneyDtlDao;
import com.godrej.properties.model.EnqJourneyDtl;


@Repository("enqJourneyDtlDao")
public class EnqJourneyDtlDaoImpl extends AbstractDao<Integer, EnqJourneyDtl> implements EnqJourneyDtlDao {

	static Logger logger = Logger.getLogger(EnqJourneyDtlDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<EnqJourneyDtl> getAllData(String projectid,String enquiryid) {
		Session session = this.sessionFactory.getCurrentSession();	
		List<EnqJourneyDtl> list=null;
		if((projectid!=null && projectid.length()>0) && (enquiryid!=null && enquiryid.length()>0))
			list =session.createQuery(" from EnqJourneyDtl WHERE  propstrength__project__c='"+projectid+"' and enquiry_sfid='"+enquiryid+"' ").list();
		else if(enquiryid!=null && enquiryid.length()>0)
			list =session.createQuery(" from EnqJourneyDtl WHERE enquiry_sfid='"+enquiryid+"' ").list(); 
		if(list!=null && !list.isEmpty())
		{
			return list;
		}
		return list;
	}
}