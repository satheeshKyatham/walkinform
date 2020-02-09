package com.godrej.properties.daoimpl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PrepaymentDao;
import com.godrej.properties.model.Prepayment;

@Repository
public class PrepaymentDaoImpl extends AbstractDao<Prepayment, Prepayment> implements PrepaymentDao{

	@Autowired
	private SessionFactory sessionFactory;	

	@Override
	public List<Prepayment> getPrepayments(String offerId) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder jpql =  new StringBuilder();
		jpql.append(" from Prepayment where offerSfid = :offerSfid");
		Query query = session.createQuery(jpql.toString());
		query.setParameter("offerSfid", offerId);		
		return query.getResultList();
	}

}
