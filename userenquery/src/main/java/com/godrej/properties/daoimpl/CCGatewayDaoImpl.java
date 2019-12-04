package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CCGatewayDao;
import com.godrej.properties.model.CCGatewayRequest;

@Repository("cCGatwayDao")
public class CCGatewayDaoImpl  extends AbstractDao<Integer, CCGatewayRequest> implements CCGatewayDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CCGatewayRequest insertRequest(CCGatewayRequest ccRequest) {
		
		persist(ccRequest);
		return ccRequest;
	}

}
