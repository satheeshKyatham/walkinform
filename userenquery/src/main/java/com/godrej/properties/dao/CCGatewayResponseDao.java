package com.godrej.properties.dao;

import com.godrej.properties.model.CCGatewayResponse;

public interface CCGatewayResponseDao {

	public CCGatewayResponse insertResponse(CCGatewayResponse ccResponse);
	public CCGatewayResponse getTinyURL(String orderID);
}
