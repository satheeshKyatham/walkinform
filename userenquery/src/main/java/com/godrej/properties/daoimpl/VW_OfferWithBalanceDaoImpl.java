package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.VW_OfferWithBalanceDao;
import com.godrej.properties.model.Vw_OfferWithBalance;

@Repository("vw_OfferWithBalanceDao")
public class VW_OfferWithBalanceDaoImpl implements VW_OfferWithBalanceDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Vw_OfferWithBalance> getOfferListList(String whereCondition) {
		Session session = this.sessionFactory.getCurrentSession();	
		
		System.out.println(" from Vw_OfferWithBalance where isactive='A' "+whereCondition+" ");
		
		List<Vw_OfferWithBalance> list =session.createQuery(" from Vw_OfferWithBalance where isactive='A' "+whereCondition+" ").list();
		if(list.size()>0)
		{
			return list;
		}
		return null;
	}

}
