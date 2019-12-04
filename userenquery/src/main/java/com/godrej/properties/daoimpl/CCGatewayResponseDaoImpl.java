package com.godrej.properties.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CCGatewayResponseDao;
import com.godrej.properties.model.AssignedUser;
import com.godrej.properties.model.CCGatewayResponse;
@Repository("cCGatwayRequestDao")
public class CCGatewayResponseDaoImpl  extends AbstractDao<Integer, CCGatewayResponse> implements CCGatewayResponseDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public CCGatewayResponse insertResponse(CCGatewayResponse ccResponse) {
		persist(ccResponse);
		
		return ccResponse;
	}
	@Override
	public CCGatewayResponse getTinyURL(String orderID) {
		Session session = this.sessionFactory.getCurrentSession();
		List<CCGatewayResponse> list =null;
		 list =session.createQuery(" from CCGatewayResponse where  invoice_id = '"+orderID+"'").list();
	 
		if(list.size()>0)
			return list.get(0);
		return null;
	}

}
