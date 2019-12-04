package com.godrej.properties.service;

import com.godrej.properties.model.CCGatewayRequest;
import com.godrej.properties.model.CCGatewayResponse;

public interface CCGatewayService {

	public CCGatewayRequest insertRequest(CCGatewayRequest ccRequest);
	
	public CCGatewayResponse insertResponse(CCGatewayResponse ccResponse);
	
	public CCGatewayResponse getTinyURL(String orderID);
	
	public String setPaymentSuccessResponse(String paymentResp);
}
