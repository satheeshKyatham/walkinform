package com.godrej.properties.dao;

import com.godrej.properties.model.CCAvenueGatewayRequest;
import com.godrej.properties.model.CCAvenueResponseModel;

public interface CCAvenueGatewayRequestDao {
	public CCAvenueGatewayRequest insertCCAvenueGatewayRequest(CCAvenueGatewayRequest gatewayRequest);
	public CCAvenueGatewayRequest updateCCAvenueResponse(CCAvenueResponseModel responseModel);
	public CCAvenueGatewayRequest getCCAvenueRequest(String orderid);
}
