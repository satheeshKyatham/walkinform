package com.godrej.properties.daoimpl;

import java.util.ArrayList;
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
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.GeneratePayment;

@SuppressWarnings("unchecked")
@Repository("generatePaymentDao")
public class GeneratePaymentDaoImpl extends AbstractDao<Integer, GeneratePayment> implements GeneratePaymentDao {
	private Logger Log = LoggerFactory.getLogger(getClass());
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertPaymentDtl(List<GeneratePayment> pymtDtl) {
		batchPersist(pymtDtl);
	}	
	
	@Override
	public List<GeneratePayment> getPaymentRecord(String enqSfid,String projectid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<GeneratePayment> authors=new ArrayList<GeneratePayment>();
		
		/*Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cc_payment_request "
				+ " where enquiry_sfid = '"+enqSfid+"'and project_sfid='"+projectid+"' AND isactive = 'Y' order by id ", GeneratePayment.class);
		authors = q.getResultList();*/
		List<GeneratePayment> list = session.createQuery(" from GeneratePayment where "
				+ "enquiry_sfid = '"+enqSfid+"'and project_sfid='"+projectid+"' and isactive='Y'").list();
		if (list.size() > 0)
			return list;

		return authors;
	}
}