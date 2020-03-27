package com.godrej.properties.daoimpl;

import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CCAvenueGatewayRequestDao;
import com.godrej.properties.model.CCAvenueGatewayRequest;

@Repository("ccAvenueGatewayRequestDao")
public class CCAvenueGatewayRequestDaoImpl extends AbstractDao<Integer, CCAvenueGatewayRequest> implements CCAvenueGatewayRequestDao{

	
	@Override
	public CCAvenueGatewayRequest insertCCAvenueGatewayRequest(CCAvenueGatewayRequest gatewayRequest) {
		persist(gatewayRequest);
		return gatewayRequest;
	}

}
