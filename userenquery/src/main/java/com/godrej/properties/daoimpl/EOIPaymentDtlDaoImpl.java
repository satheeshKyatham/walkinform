package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.EOIPaymentDtlDao;
import com.godrej.properties.model.ApplicantDtl;
import com.godrej.properties.model.EOIPaymentDtl;
import com.godrej.properties.model.ExtraCharges;

@SuppressWarnings("unchecked")
@Repository("eOIpaymentDtlDao")
public class EOIPaymentDtlDaoImpl extends AbstractDao<Integer, EOIPaymentDtl> implements EOIPaymentDtlDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void insertPaymentDtl(List<EOIPaymentDtl> pymtDtl) {
		batchPersist(pymtDtl);
	}	
	
	
	
	
	@Override
	public List<EOIPaymentDtl> getEOIPaymentRecord(String enqSfid) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		List<EOIPaymentDtl> authors=null;
		
		Query q = session.createNativeQuery("SELECT * FROM salesforce.gpl_cs_eoi_payment_details "
				//+ " where enq_sfid = '"+enqSfid+"' and isactive = 'Y' order by gpl_cs_eoi_payment_details_id ", EOIPaymentDtl.class);
				+ " where enq_sfid = '"+enqSfid+"' order by gpl_cs_eoi_payment_details_id ", EOIPaymentDtl.class);
		//order by b.id
		authors = q.getResultList();
		
		System.out.println("Final Size::"+authors.size());
		
		if (authors.size() > 0)
			return authors;

		return null;
	}
	
	
	@Override
	public void updateEOIForOffer(List<EOIPaymentDtl> charges) {
		for (int i = 0; i <charges.size(); i++) {
			Session session = this.sessionFactory.getCurrentSession();
			
			System.out.println("Test ::: " + charges.get(i).getId());
			
			charges.get(i).getDescription();
			Query query = session.createQuery("UPDATE EOIPaymentDtl SET isactive = 'O' WHERE id = '"+charges.get(i).getId()+"' ");
			
			query.executeUpdate();
		}
	}
	
	
}