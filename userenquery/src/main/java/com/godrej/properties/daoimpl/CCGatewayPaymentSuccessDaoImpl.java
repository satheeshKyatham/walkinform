package com.godrej.properties.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.godrej.properties.dao.AbstractDao;
import com.godrej.properties.dao.CCGatewayPaymentSuccessDao;
import com.godrej.properties.model.CCGatewayPaymentResp;

@Repository("cCGatewayPaymentSuccessDao")
public class CCGatewayPaymentSuccessDaoImpl extends AbstractDao<Integer, CCGatewayPaymentResp> implements CCGatewayPaymentSuccessDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public CCGatewayPaymentResp setPaymentSuccessResponse(CCGatewayPaymentResp paymentResp) {
		
		persist(paymentResp);
		return paymentResp;
	}

}
