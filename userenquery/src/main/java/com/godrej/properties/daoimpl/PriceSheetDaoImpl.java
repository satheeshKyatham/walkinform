package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.PriceSheetDao;
import com.godrej.properties.model.PriceSheet;
import com.godrej.properties.model.Token;

@Repository("priceDao")
public class PriceSheetDaoImpl extends AbstractDao<Integer, PriceSheet> implements PriceSheetDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public String checkPriceDetails(PriceSheet pricesheet) {
		String retun ="";
		Session session = this.sessionFactory.getCurrentSession();	
		List<Token> list =session.createQuery(" from PriceSheet where phone_no='"+pricesheet.getPhone_no()+"'").list();
		if(list.size()>0)
		{
			retun="Already Exists";
		}
		else
		{
			persist(pricesheet);
			retun="Submitted";
			//Already Exisit
		}
		
		
		return retun;
	}

}
